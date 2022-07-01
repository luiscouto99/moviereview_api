package mindswap.academy.moviereview_api.mockedpojo;

import mindswap.academy.moviereview_api.command.review.ReviewDto;
import mindswap.academy.moviereview_api.persistence.model.movie.Movie;
import mindswap.academy.moviereview_api.persistence.model.review.Review;
import mindswap.academy.moviereview_api.persistence.model.review.rating.Rating;
import mindswap.academy.moviereview_api.persistence.model.user.User;

import static mindswap.academy.moviereview_api.mockedpojo.UserMockedPojo.USER_EXAMPLE;

public class ReviewMockedPojo {

    public static final Rating RATING_EXAMPLE = Rating.builder()
            .id(1L)
            .rate("★✰✰✰✰")
            .build();

    public static final Movie MOVIE_EXAMPLE = Movie.builder()
            .id(1L)
            .title("something")
            .fullTitle("something")
            .year("something")
            .releaseDate("something")
            .runtimeStr("something")
            .contentRating("something")
            .build();

    public static final Review REVIEW_EXAMPLE = Review.builder()
            .id(1L)
            .review("asdasdasd")
            .ratingId(RATING_EXAMPLE)
            .movieId(MOVIE_EXAMPLE)
            .userId(
                    User.builder()
                            .id(1L)
                            .build()
            )
            .build();

    public static final ReviewDto REVIEW_DTO_EXAMPLE = ReviewDto.builder()
            .id(1L)
            .review("asdasdasd")
            .ratingId(1L)
            .movieId(1L)
            .userId(1L)
            .build();
}