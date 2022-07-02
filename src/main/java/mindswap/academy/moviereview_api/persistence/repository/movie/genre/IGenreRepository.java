package mindswap.academy.moviereview_api.persistence.repository.movie.genre;

import mindswap.academy.moviereview_api.persistence.model.movie.genre.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IGenreRepository extends JpaRepository<Genre,Long> {
   Optional<Genre> findByValue(String value);
    @Query(value = "SELECT genre.*\n" +
            "FROM movie\n" +
            "INNER JOIN genre_movie_list\n" +
            "ON genre_movie_list.movie_id = movie.id \n" +
            "INNER JOIN genre\n" +
            "ON genre_movie_list.genre_id = genre.id \n" +
            "WHERE genre.id = :id ;", nativeQuery = true)
    Optional<Object> checkIfGenreIsBeingUsed(Long id);
}
