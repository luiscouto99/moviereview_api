package mindswap.academy.moviereview_api.service.review.rating;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.command.review.rating.RatingDto;
import mindswap.academy.moviereview_api.command.review.rating.RatingUpdateDto;
import mindswap.academy.moviereview_api.converter.review.rating.IRatingConverter;
import mindswap.academy.moviereview_api.exception.NotFoundException;
import mindswap.academy.moviereview_api.exception.RatingNotFoundException;
import mindswap.academy.moviereview_api.persistence.model.review.rating.Rating;
import mindswap.academy.moviereview_api.persistence.repository.review.rating.IRatingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static mindswap.academy.moviereview_api.exception.ExceptionMessages.*;

@Service
@RequiredArgsConstructor
public class RatingService implements IRatingService{

    private final IRatingRepository iRatingRepository;
    private final IRatingConverter iRatingConverter;

    @Override
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
        this.iRatingRepository.save(rating);
        return this.iRatingConverter.converter(rating, RatingDto.class);
    }

    @Override
    public ResponseEntity<Object> delete(Long id) {
        Rating rating = this.iRatingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(RATING_NOT_FOUND));
        this.iRatingRepository.delete(rating);
        return ResponseEntity.status(HttpStatus.OK).body("Rating deleted");
    }

    @Override
    public RatingDto update(Long id, RatingUpdateDto ratingUpdateDto) {
        Rating oldRatingAttributes = this.iRatingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(RATING_NOT_FOUND));
        Rating newRatingAttributes = this.iRatingConverter.converterUpdate(ratingUpdateDto, oldRatingAttributes);
        this.iRatingRepository.save(newRatingAttributes);
        return this.iRatingConverter.converter(newRatingAttributes, RatingDto.class);
    }
}
