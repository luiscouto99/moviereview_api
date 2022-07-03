package mindswap.academy.moviereview_api.service.review;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.command.review.ReviewDeleteDto;
import mindswap.academy.moviereview_api.command.review.ReviewDto;
import mindswap.academy.moviereview_api.command.review.ReviewUpdateDto;
import mindswap.academy.moviereview_api.config.CheckAuth;
import mindswap.academy.moviereview_api.converter.review.IReviewConverter;
import mindswap.academy.moviereview_api.exception.BadRequestException;
import mindswap.academy.moviereview_api.exception.ConflictException;
import mindswap.academy.moviereview_api.exception.NotFoundException;
import mindswap.academy.moviereview_api.persistence.model.movie.Movie;
import mindswap.academy.moviereview_api.persistence.model.review.Review;
import mindswap.academy.moviereview_api.persistence.model.review.rating.Rating;
import mindswap.academy.moviereview_api.persistence.model.user.User;
import mindswap.academy.moviereview_api.persistence.repository.movie.IMovieRepository;
import mindswap.academy.moviereview_api.persistence.repository.review.IReviewRepository;
import mindswap.academy.moviereview_api.persistence.repository.review.rating.IRatingRepository;
import mindswap.academy.moviereview_api.persistence.repository.user.IUserRepository;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static mindswap.academy.moviereview_api.exception.ExceptionMessages.*;

@Service
@RequiredArgsConstructor
public class ReviewService implements IReviewService {

    private final IReviewRepository iReviewRepository;
    private final IRatingRepository iRatingRepository;
    private final IUserRepository iUserRepository;
    private final IMovieRepository iMovieRepository;
    private final IReviewConverter iReviewConverter;
    private final CacheManager cacheManager;
    private final CheckAuth checkAuth;

    @Override
    @Cacheable("reviews")
    public List<ReviewDto> getAll() {
        List<Review> reviewList = this.iReviewRepository.findAll();
        return this.iReviewConverter.converterList(reviewList, ReviewDto.class);
    }

    @Override
    @Cacheable("reviews")
    public List<ReviewDto> getReviewsFromUser(Long id) {
        User user = this.iUserRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));

        return this.iReviewConverter.converterList(user.getReviewList(), ReviewDto.class);
    }

    @Override
    @Cacheable("reviews")
    public List<ReviewDto> searchBy(Long ratingId, Long movieId, Long userId) {
        if (ratingId == null && movieId == null && userId == null) {
            throw new BadRequestException(AT_LEAST_1_PARAMETER);
        }

        List<Review> reviewList = iReviewRepository.searchBy(ratingId, movieId, userId);

        if (reviewList.isEmpty()) {
            throw new NotFoundException(REVIEW_NOT_FOUND);
        }

        return this.iReviewConverter.converterList(reviewList, ReviewDto.class);
    }

    @Override
    public ReviewDto add(ReviewDto reviewDto) {
        checkAuth.checkIfUserEqualsIdGiven(reviewDto.getUserId());

        Review review = this.iReviewConverter.converter(reviewDto, Review.class);
        this.iUserRepository.findById(reviewDto.getUserId())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND)
                );

        this.iMovieRepository.findById(reviewDto.getMovieId())
                .orElseThrow(() -> new NotFoundException(MOVIE_NOT_FOUND)
                );

        this.iReviewRepository.findIfReviewAlreadyExists(reviewDto.getUserId(), reviewDto.getMovieId())
                .ifPresent((reviewByUser) -> {
                    throw new ConflictException(REVIEW_ALREADY_EXISTS);
                });

        this.iReviewRepository.save(review);

        Movie movie = this.iMovieRepository.findById(reviewDto.getMovieId())
                .orElseThrow(() -> new NotFoundException(MOVIE_NOT_FOUND));
        List<Review> movieReviews = this.iReviewRepository.searchAllMovieId(movie.getId());

        // calcular a média dos ratings do filme
        Double movieRating = movieReviews.stream()
                .mapToLong(x -> x.getRatingId().getId())
                .average().orElse(0);

        movie.setRatingId(this.iRatingRepository.findById(Math.round(movieRating)).get());
        movie.setTotalReviews(movieReviews.size());

        clearReviewCache();
        this.iMovieRepository.save(movie);
        return this.iReviewConverter.converter(review, ReviewDto.class);
    }

    public ResponseEntity<Object> delete(ReviewDeleteDto reviewDeleteDto) {
        checkAuth.checkIfUserEqualsIdGiven(reviewDeleteDto.getUserId());
        Review review = this.iReviewRepository.searchByUserIdAndReviewId(reviewDeleteDto.getUserId(),reviewDeleteDto.getId())
                .orElseThrow(() -> new NotFoundException(REVIEW_NOT_FOUND));

        clearReviewCache();
        this.iReviewRepository.delete(review);
        return ResponseEntity.status(HttpStatus.OK).body("Review deleted");
    }

    @Override
    @CacheEvict(key = "#id", value = "review")
    public ReviewDto update(Long id, ReviewUpdateDto reviewUpdateDto) {
        checkAuth.checkIfUserEqualsIdGiven(reviewUpdateDto.getUserId());
        this.iUserRepository.findById(reviewUpdateDto.getUserId())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));

        this.iMovieRepository.findById(reviewUpdateDto.getMovieId())
                .orElseThrow(() -> new NotFoundException(MOVIE_NOT_FOUND));

        Review oldReviewAttributes = this.iReviewRepository.searchByUserIdAndReviewId(reviewUpdateDto.getUserId(),id)
                .orElseThrow(() -> new NotFoundException(REVIEW_NOT_FOUND));

        Rating rating = this.iRatingRepository.findById(reviewUpdateDto.getRatingId())
                .orElseThrow(() -> new NotFoundException(REVIEW_NOT_FOUND));

        updatingReview(reviewUpdateDto, oldReviewAttributes, rating);
        updateMovieRating(oldReviewAttributes);

        clearReviewCache();
        this.iReviewRepository.save(oldReviewAttributes);
        return this.iReviewConverter.converter(oldReviewAttributes, ReviewDto.class);
    }

    private void clearReviewCache() {
        Cache reviewCache = this.cacheManager.getCache("reviews");
        if(reviewCache!=null)reviewCache.clear();
    }

    private void updatingReview(ReviewUpdateDto reviewUpdateDto, Review oldReviewAttributes, Rating rating) {
        oldReviewAttributes.setReview(reviewUpdateDto.getReview());
        oldReviewAttributes.setRatingId(rating);
    }

    private void updateMovieRating(Review oldReviewAttributes) {
        Movie movie = this.iMovieRepository.findById(oldReviewAttributes.getMovieId().getId())
                .orElseThrow(() -> new NotFoundException(MOVIE_NOT_FOUND));
        List<Review> movieReviews = this.iReviewRepository.searchAllMovieId(movie.getId());

        // calcular a média dos ratings do filme
        Double movieRating = movieReviews.stream()
                .mapToLong(x -> x.getRatingId().getId())
                .average().orElse(0);

        movie.setRatingId(this.iRatingRepository.findById(Math.round(movieRating)).get());
    }
}
