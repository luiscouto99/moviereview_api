package mindswap.academy.moviereview_api.service.review.rating;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.command.review.rating.RatingDto;
import mindswap.academy.moviereview_api.command.review.rating.RatingUpdateDto;
import mindswap.academy.moviereview_api.converter.review.rating.IRatingConverter;
import mindswap.academy.moviereview_api.exception.NotFoundException;
import mindswap.academy.moviereview_api.persistence.model.review.rating.Rating;
import mindswap.academy.moviereview_api.persistence.repository.movie.IMovieRepository;
import mindswap.academy.moviereview_api.persistence.repository.review.IReviewRepository;
import mindswap.academy.moviereview_api.persistence.repository.review.rating.IRatingRepository;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static mindswap.academy.moviereview_api.exception.ExceptionMessages.*;

@Service
@RequiredArgsConstructor
public class RatingService implements IRatingService {

    private final IRatingRepository iRatingRepository;
    private final IRatingConverter iRatingConverter;
    private final IMovieRepository iMovieRepository;
    private final IReviewRepository iReviewRepository;
    private final CacheManager cacheManager;

    @Override
    @Cacheable("ratings")
    public List<RatingDto> getAll() {
        List<Rating> ratingList = this.iRatingRepository.findAll();

        if (ratingList.isEmpty()) {
            throw new NotFoundException(RATING_NOT_FOUND);
        }

        return this.iRatingConverter.converterList(ratingList, RatingDto.class);
    }

    @Override
    public RatingDto add(RatingDto ratingDto) {
        Rating rating = this.iRatingConverter.converter(ratingDto, Rating.class);
        Objects.requireNonNull(this.cacheManager.getCache("ratings")).clear();
        this.iRatingRepository.save(rating);
        return this.iRatingConverter.converter(rating, RatingDto.class);
    }

    @Override
    @CacheEvict(key = "#id", value = "rating")
    public ResponseEntity<Object> delete(Long id) {
        Rating rating = this.iRatingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(RATING_NOT_FOUND));
        Objects.requireNonNull(this.cacheManager.getCache("ratings")).clear();
        this.iRatingRepository.delete(rating);
        return ResponseEntity.status(HttpStatus.OK).body("Rating deleted");
    }

    @Override
    @CacheEvict(key = "#id", value = "rating")
    public RatingDto update(Long id, RatingUpdateDto ratingUpdateDto) {
        Rating oldRatingAttributes = this.iRatingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(RATING_NOT_FOUND));
        Rating newRatingAttributes = this.iRatingConverter.converterUpdate(ratingUpdateDto, oldRatingAttributes);

        Objects.requireNonNull(this.cacheManager.getCache("ratings")).clear();
        this.iRatingRepository.save(newRatingAttributes);
        return this.iRatingConverter.converter(newRatingAttributes, RatingDto.class);
    }
}
