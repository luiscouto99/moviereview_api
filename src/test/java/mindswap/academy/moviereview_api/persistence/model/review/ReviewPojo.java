package mindswap.academy.moviereview_api.persistence.model.review;

import mindswap.academy.moviereview_api.command.review.ReviewDto;
import mindswap.academy.moviereview_api.command.review.ReviewUpdateDto;
import mindswap.academy.moviereview_api.persistence.model.movie.Movie;
import mindswap.academy.moviereview_api.persistence.model.movie.actor.Actor;
import mindswap.academy.moviereview_api.persistence.model.review.Review;
import mindswap.academy.moviereview_api.persistence.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class ReviewPojo {
    // Models
    public static final Review REVIEW_EXAMPLE = Review.builder()
            .id(1L)
            .userId(User.builder().id(1L).build())
            .movieId(Movie.builder().id(1L).build())
            .review("something")
            .build();

    public static final ReviewDto REVIEW_DTO_EXAMPLE = ReviewDto.builder()
            .id(1L)
            .userId(1L)
            .movieId(1L)
            .ratingId(1L)
            .review("something")
            .build();

    public static final ReviewUpdateDto REVIEW_UPDATE_DTO_EXAMPLE = ReviewUpdateDto.builder()
            .userId(1L)
            .movieId(1L)
            .ratingId(1L)
            .review("something")
            .build();

    // Lists
    public static final List<Review> REVIEW_LIST_EXAMPLE = new ArrayList<>(List.of(
            Review.builder()
                    .id(1L)
                    .userId(User.builder().id(1L).build())
                    .movieId(Movie.builder().id(1L).build())
                    .review("something")
                    .build()
    ));

    public static final List<ReviewDto> REVIEW_DTO_LIST_EXAMPLE = new ArrayList<>(List.of(
            ReviewDto.builder()
                    .id(1L)
                    .userId(1L)
                    .movieId(1L)
                    .ratingId(1L)
                    .review("something")
                    .build()
    ));
}
