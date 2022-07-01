package mindswap.academy.moviereview_api.persistence.repository.movie.genre;

import mindswap.academy.moviereview_api.persistence.model.movie.genre.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IGenreRepository extends JpaRepository<Genre,Long> {
   Optional<Genre> findByValue(String value);
}
