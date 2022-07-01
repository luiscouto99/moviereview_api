package mindswap.academy.moviereview_api.service.review;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.command.review.ReviewDto;
import mindswap.academy.moviereview_api.command.review.ReviewUpdateDto;
import mindswap.academy.moviereview_api.converter.review.IReviewConverter;
import mindswap.academy.moviereview_api.converter.user.IUserConverter;
import mindswap.academy.moviereview_api.exception.NotFoundException;
import mindswap.academy.moviereview_api.exception.ReviewNotFoundException;
import mindswap.academy.moviereview_api.exception.UserNotFoundException;
import mindswap.academy.moviereview_api.persistence.model.review.Review;
import mindswap.academy.moviereview_api.persistence.model.review.rating.Rating;
import mindswap.academy.moviereview_api.persistence.model.user.User;
import mindswap.academy.moviereview_api.persistence.repository.review.IReviewRepository;
import mindswap.academy.moviereview_api.persistence.repository.review.rating.IRatingRepository;
import mindswap.academy.moviereview_api.persistence.repository.user.IUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static mindswap.academy.moviereview_api.exception.ExceptionMessages.*;

@Service
@RequiredArgsConstructor
public class ReviewService implements IReviewService {

    private final IReviewRepository iReviewRepository;
    private final IReviewConverter iReviewConverter;
    private final IRatingRepository iRatingRepository;
    private final IUserRepository iUserRepository;

    @Override
    public List<ReviewDto> getAll() {
        List<Review> reviewList = this.iReviewRepository.findAll();

        if (reviewList.isEmpty()) {
            throw new NotFoundException(REVIEW_NOT_FOUND);
        }

        return this.iReviewConverter.converterList(reviewList, ReviewDto.class);
    }

    @Override
    public List<ReviewDto> getReviewsFromUser(Long id) {
        User user = this.iUserRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));

        return this.iReviewConverter.converterList(user.getReviewList(), ReviewDto.class);
    }

    @Override
    public ReviewDto add(ReviewDto reviewDto) {
        Review review = this.iReviewConverter.converter(reviewDto, Review.class);
        this.iReviewRepository.save(review);
        return this.iReviewConverter.converter(review, ReviewDto.class);
    }

    @Override
    public ResponseEntity<Object> delete(Long id) {
        Review review = this.iReviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(REVIEW_NOT_FOUND));
        this.iReviewRepository.delete(review);
        return ResponseEntity.status(HttpStatus.OK).body("Review deleted");
    }

    @Override
    public ReviewDto update(Long id, ReviewUpdateDto reviewUpdateDto) {
        Review oldReviewAttributes = this.iReviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(REVIEW_NOT_FOUND));

        Rating rating = this.iRatingRepository.findById(reviewUpdateDto.getRatingId())
                .orElseThrow(() -> new NotFoundException(REVIEW_NOT_FOUND));

        oldReviewAttributes.setReview(reviewUpdateDto.getReview());
        oldReviewAttributes.setRatingId(rating);

        this.iReviewRepository.save(oldReviewAttributes);
        return this.iReviewConverter.converter(oldReviewAttributes, ReviewDto.class);
    }
}
