package mindswap.academy.moviereview_api.persistence.repository.movie;

import mindswap.academy.moviereview_api.persistence.model.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, Long> {
    @Query(value = "SELECT movie.*\n" +
            "FROM movie \n" +
            "WHERE (movie.id = :id OR :id ISNULL)\n" +
            "AND\n" +
            "(LOWER(movie.title) LIKE LOWER(CONCAT('%',:title,'%')) OR :title ISNULL) \n" +
            "AND\n" +
            "(movie.year LIKE :year OR :year ISNULL)\n" +
            "AND\n" +
            "(LOWER(movie.content_rating) LIKE LOWER(:contentRanting) OR :contentRanting ISNULL);", nativeQuery = true)
    List<Movie> searchBy(Long id, String title, String year,String contentRanting);
    @Query(value = "SELECT movie.*\n" +
            "FROM genre \n" +
            "INNER JOIN\n" +
            "genre_movie_list \n" +
            "ON genre.id = genre_movie_list.genre_id\n" +
            "INNER JOIN \n" +
            "movie\n" +
            "ON genre_movie_list.movie_id = movie.id\n" +
            "WHERE (LOWER(genre.value)  LIKE LOWER(:genre) OR :genre ISNULL);", nativeQuery = true)
    List<Movie> searchByGenre(String genre);
    @Query(value ="SELECT movie.*\n" +
            "FROM actor \n" +
            "JOIN\n" +
            "actor_movie_list\n" +
            "ON\n" +
            "actor.id = actor_movie_list.actor_id \n" +
            "JOIN \n" +
            "movie \n" +
            "ON movie.id = actor_movie_list.movie_id \n" +
            "WHERE (LOWER(actor.name) LIKE LOWER(CONCAT('%',:name,'%')) OR :name ISNULL);", nativeQuery = true)
    List<Movie> searchHowManyMoviesActorHasByName(String name);
}
