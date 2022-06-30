package mindswap.academy.moviereview_api.service.review;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.command.review.ReviewDto;
import mindswap.academy.moviereview_api.command.review.ReviewUpdateDto;
import mindswap.academy.moviereview_api.converter.review.IReviewConverter;
import mindswap.academy.moviereview_api.persistence.model.review.Review;
import mindswap.academy.moviereview_api.persistence.model.review.rating.Rating;
import mindswap.academy.moviereview_api.persistence.repository.review.IReviewRepository;
import mindswap.academy.moviereview_api.persistence.repository.review.rating.IRatingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService implements IReviewService {

    private final IReviewRepository iReviewRepository;
    private final IReviewConverter iReviewConverter;
    private final IRatingRepository iRatingRepository;

    @Override
    public List<ReviewDto> getAll() {
        List<Review> reviewList = this.iReviewRepository.findAll();

        // para quando tiver custom exceptions
        /*if (reviewList.isEmpty()) {
            throw new BlaBlaBlaException();
        }*/

        return this.iReviewConverter.converterList(reviewList, ReviewDto.class);
    }

    @Override
    public ReviewDto add(ReviewDto reviewDto) {
        Review review = this.iReviewConverter.converter(reviewDto, Review.class);
        this.iReviewRepository.save(review);
        return this.iReviewConverter.converter(review, ReviewDto.class);
    }

    @Override
    public ResponseEntity<Object> delete(Long id) {
        Review review = this.iReviewRepository.findById(id).orElse(null);
        this.iReviewRepository.delete(review);
        return ResponseEntity.status(HttpStatus.OK).body("Review deleted");
    }

    @Override
    public ReviewDto update(Long id, ReviewUpdateDto reviewUpdateDto) {
        Review oldReviewAttributes = this.iReviewRepository.findById(id).orElse(null);

        Rating rating = this.iRatingRepository.findById(reviewUpdateDto.getRatingId()).orElse(null);
        oldReviewAttributes.setReview(reviewUpdateDto.getReview());
        oldReviewAttributes.setRatingId(rating);

        this.iReviewRepository.save(oldReviewAttributes);
        return this.iReviewConverter.converter(oldReviewAttributes, ReviewDto.class);
    }
}
