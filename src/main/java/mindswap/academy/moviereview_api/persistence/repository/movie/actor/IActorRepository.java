package mindswap.academy.moviereview_api.persistence.repository.movie.actor;

import mindswap.academy.moviereview_api.persistence.model.movie.actor.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IActorRepository extends JpaRepository<Actor,Long> {
    @Query(value = "SELECT actor.*\n" +
            "FROM movie\n" +
            "INNER JOIN actor_movie_list\n" +
            "ON actor_movie_list.movie_id = movie.id \n" +
            "INNER JOIN actor\n" +
            "ON actor_movie_list.actor_id = actor.id \n" +
            "WHERE actor.id = :id ;", nativeQuery = true)
    Optional<Object> checkIfActorIsBeingUsed(Long id);
    Optional<Actor> findByName(String name);
}
