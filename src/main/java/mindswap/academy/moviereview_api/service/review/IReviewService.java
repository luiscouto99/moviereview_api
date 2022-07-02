package mindswap.academy.moviereview_api.service.review;

import mindswap.academy.moviereview_api.command.review.ReviewDto;
import mindswap.academy.moviereview_api.command.review.ReviewUpdateDto;
import mindswap.academy.moviereview_api.persistence.model.movie.Movie;
import mindswap.academy.moviereview_api.persistence.model.review.rating.Rating;
import mindswap.academy.moviereview_api.persistence.model.user.User;
import mindswap.academy.moviereview_api.service.IGenericService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IReviewService extends IGenericService<ReviewDto, ReviewDto, ReviewUpdateDto> {
    List<ReviewDto> getReviewsFromUser(Long id);

    List<ReviewDto> searchBy(Long ratingId, Long movieId, Long userId);
}
