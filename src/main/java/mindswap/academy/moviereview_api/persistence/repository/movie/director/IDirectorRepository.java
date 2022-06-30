package mindswap.academy.moviereview_api.persistence.repository.movie.director;

import mindswap.academy.moviereview_api.persistence.model.movie.director.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDirectorRepository extends JpaRepository<Director,Long> {
}
