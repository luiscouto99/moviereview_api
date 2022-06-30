package mindswap.academy.moviereview_api.persistence.repository.review.rating;

import mindswap.academy.moviereview_api.persistence.model.review.rating.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRatingRepository extends JpaRepository<Rating, Long> {
}
