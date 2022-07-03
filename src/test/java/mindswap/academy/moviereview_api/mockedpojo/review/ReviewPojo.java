package mindswap.academy.moviereview_api.mockedpojo.review;

import mindswap.academy.moviereview_api.persistence.model.movie.actor.Actor;
import mindswap.academy.moviereview_api.persistence.model.review.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewPojo {
    // Models
    public static final Review REVIEW_EXAMPLE = Review.builder()
            .id(1L)
            .review("something")
            .build();

    // Lists
    public static final List<Review> REVIEW_LIST_EXAMPLE = new ArrayList<>(List.of(
            Review.builder()
                    .id(1L)
                    .review("something")
                    .build()
    ));
}
