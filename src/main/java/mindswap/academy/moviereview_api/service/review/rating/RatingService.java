package mindswap.academy.moviereview_api.service.review.rating;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.command.review.rating.RatingDto;
import mindswap.academy.moviereview_api.command.review.rating.RatingUpdateDto;
import mindswap.academy.moviereview_api.converter.review.rating.IRatingConverter;
import mindswap.academy.moviereview_api.persistence.model.review.rating.Rating;
import mindswap.academy.moviereview_api.persistence.repository.review.rating.IRatingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService implements IRatingService{

    private final IRatingRepository iRatingRepository;
    private final IRatingConverter iRatingConverter;

    @Override
    public List<RatingDto> getAll() {
        List<Rating> ratingList = this.iRatingRepository.findAll();

        // para quando tiver custom exceptions
        /*if (ratingList.isEmpty()) {
            throw new BlaBlaBlaException();
        }*/

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
        Rating rating = this.iRatingRepository.findById(id).orElse(null);
        this.iRatingRepository.delete(rating);
        return ResponseEntity.status(HttpStatus.OK).body("Rating deleted");
    }

    @Override
    public RatingDto update(Long id, RatingUpdateDto ratingUpdateDto) {
        Rating oldRatingAttributes = this.iRatingRepository.findById(id).orElse(null);
        Rating newRatingAttributes = this.iRatingConverter.converterUpdate(ratingUpdateDto, oldRatingAttributes);
        this.iRatingRepository.save(newRatingAttributes);
        return this.iRatingConverter.converter(newRatingAttributes, RatingDto.class);
    }
}
