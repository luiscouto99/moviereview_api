package mindswap.academy.moviereview_api.mockedpojo.review;

import mindswap.academy.moviereview_api.persistence.model.review.rating.Rating;

public class RatingPojo {
    public static final Rating RATING_EXAMPLE = Rating.builder()
            .id(1L)
            .rate("★✰✰✰✰")
            .build();
}
