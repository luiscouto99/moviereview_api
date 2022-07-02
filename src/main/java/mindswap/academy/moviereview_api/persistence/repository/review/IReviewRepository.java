package mindswap.academy.moviereview_api.persistence.repository.review;

import mindswap.academy.moviereview_api.command.review.ReviewDto;
import mindswap.academy.moviereview_api.persistence.model.movie.Movie;
import mindswap.academy.moviereview_api.persistence.model.review.Review;
import mindswap.academy.moviereview_api.persistence.model.review.rating.Rating;
import mindswap.academy.moviereview_api.persistence.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReviewRepository extends JpaRepository<Review, Long> {
    @Query(value = "SELECT *\n" +
            "FROM review \n" +
            "WHERE review.movie_id_fk = :id ;", nativeQuery = true)
    List<Review> searchAllMovieId(Long id);

    @Query(value = "SELECT review.*\n" +
            "FROM review \n" +
            "WHERE (rate_id_fk = :ratingId OR :ratingId ISNULL)\n" +
            "AND (user_id_fk = :userId OR :userId ISNULL)\n" +
            "AND (movie_id_fk = :movieId or :movieId ISNULL);", nativeQuery = true)
    List<Review> searchBy(Long ratingId, Long movieId, Long userId);
}
