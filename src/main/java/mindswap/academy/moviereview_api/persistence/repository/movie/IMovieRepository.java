package mindswap.academy.moviereview_api.persistence.repository.movie;

import mindswap.academy.moviereview_api.persistence.model.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, Long> {
    @Query(value = "SELECT movie.*\n" +
            "FROM genre \n" +
            "INNER JOIN\n" +
            "genre_movie_list \n" +
            "ON genre.id = genre_movie_list.genre_id\n" +
            "INNER JOIN \n" +
            "movie\n" +
            "ON genre_movie_list.movie_id = movie.id\n" +
            "WHERE (movie.id = :id OR :id ISNULL)\n" +
            "AND\n" +
            "(movie.title LIKE CONCAT('%',:title,'%') OR :title ISNULL) \n" +
            "AND\n" +
            "(movie.year LIKE :year OR :year ISNULL)\n" +
            "AND\n" +
            "(genre.value  LIKE :genre OR :genre ISNULL);", nativeQuery = true)
    List<Movie> searchBy(Long id, String title, String year, String genre);
}
