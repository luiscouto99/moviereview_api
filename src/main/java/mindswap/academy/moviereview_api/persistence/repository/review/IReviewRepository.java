package mindswap.academy.moviereview_api.persistence.repository.review;

import mindswap.academy.moviereview_api.persistence.model.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IReviewRepository extends JpaRepository<Review, Long> {
    @Query(value = "SELECT *\n" +
            "FROM review \n" +
            "WHERE review.movie_id_fk = :id ;", nativeQuery = true)
    List<Review> searchAllMovieId(Long id);
}
