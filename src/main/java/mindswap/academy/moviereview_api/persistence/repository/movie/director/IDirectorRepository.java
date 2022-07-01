package mindswap.academy.moviereview_api.persistence.repository.movie.director;

import mindswap.academy.moviereview_api.persistence.model.movie.director.Director;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;
import java.util.Optional;

public interface IDirectorRepository extends JpaRepository<Director,Long> {
    Optional<Director> findByName(String name);
}
