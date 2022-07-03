package mindswap.academy.moviereview_api.service.review;

import mindswap.academy.moviereview_api.command.review.ReviewDto;
import mindswap.academy.moviereview_api.command.review.ReviewUpdateDto;
import mindswap.academy.moviereview_api.persistence.model.movie.Movie;
import mindswap.academy.moviereview_api.persistence.model.review.rating.Rating;
import mindswap.academy.moviereview_api.persistence.model.user.User;
import mindswap.academy.moviereview_api.service.IGenericService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IReviewService{
    List<ReviewDto> getReviewsFromUser(Long id);
    List<ReviewDto> searchBy(Long ratingId, Long movieId, Long userId);
    List<ReviewDto> getAll();
    ReviewDto add(ReviewDto dto);
    ReviewDto update(Long id, ReviewUpdateDto updateDto);
}
