package mindswap.academy.moviereview_api.service.review;

import mindswap.academy.moviereview_api.command.review.ReviewDto;
import mindswap.academy.moviereview_api.command.review.ReviewUpdateDto;
import mindswap.academy.moviereview_api.service.IGenericService;

import java.util.List;

public interface IReviewService extends IGenericService<ReviewDto, ReviewDto, ReviewUpdateDto> {
    List<ReviewDto> getReviewsFromUser(Long id);
}
