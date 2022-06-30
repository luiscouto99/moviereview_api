package mindswap.academy.moviereview_api.persistence.repository.movie;

import mindswap.academy.moviereview_api.persistence.model.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieRepository extends JpaRepository<Movie,Long> {
}
