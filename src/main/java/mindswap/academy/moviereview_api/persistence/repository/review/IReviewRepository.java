package mindswap.academy.moviereview_api.persistence.repository.review;

import mindswap.academy.moviereview_api.persistence.model.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReviewRepository extends JpaRepository<Review, Long> {
}
