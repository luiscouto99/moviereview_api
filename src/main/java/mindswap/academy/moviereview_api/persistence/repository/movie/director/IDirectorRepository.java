package mindswap.academy.moviereview_api.persistence.repository.movie.director;

import mindswap.academy.moviereview_api.persistence.model.movie.director.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IDirectorRepository extends JpaRepository<Director,Long> {
    @Query(value = "SELECT director.*\n" +
            "FROM movie\n" +
            "INNER JOIN director_movie_list\n" +
            "ON director_movie_list.movie_id = movie.id \n" +
            "INNER JOIN director\n" +
            "ON director_movie_list.director_id = director.id \n" +
            "WHERE director.id = :id ;", nativeQuery = true)
    Optional<Director> checkIfDirectorIsBeingUsed(Long id);

    Optional<Director> findByName(String name);
}
