package mindswap.academy.moviereview_api.service.review.rating;

import mindswap.academy.moviereview_api.command.review.rating.RatingDto;
import mindswap.academy.moviereview_api.command.review.rating.RatingUpdateDto;
import mindswap.academy.moviereview_api.service.IGenericService;

public interface IRatingService extends IGenericService<RatingDto, RatingUpdateDto> {
}
