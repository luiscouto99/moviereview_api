package mindswap.academy.moviereview_api.persistence.model.review;

import mindswap.academy.moviereview_api.command.review.ReviewDto;
import mindswap.academy.moviereview_api.persistence.model.review.rating.Rating;

public class ReviewOutside {
    private Long id;
    private ReviewDto reviewDto;
    private Rating ratingId;
}
