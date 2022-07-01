package mindswap.academy.moviereview_api.persistence.repository.movie.actor;

import mindswap.academy.moviereview_api.controller.movie.actor.ActorController;
import mindswap.academy.moviereview_api.persistence.model.movie.actor.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IActorRepository extends JpaRepository<Actor,Long> {
    Optional<Actor> findByName(String name);
}
