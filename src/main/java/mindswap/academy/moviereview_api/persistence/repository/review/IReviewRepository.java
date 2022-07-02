package mindswap.academy.moviereview_api.persistence.repository.review;

import mindswap.academy.moviereview_api.persistence.model.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Query(value = "SELECT review.*\n" +
            "FROM review \n" +
            "WHERE (user_id_fk = :userId OR :userId ISNULL)\n" +
            "AND (movie_id_fk = :movieId or :movieId ISNULL);", nativeQuery = true)
    Optional<Review> findIfReviewAlreadyExists(Long userId, Long movieId);
}
