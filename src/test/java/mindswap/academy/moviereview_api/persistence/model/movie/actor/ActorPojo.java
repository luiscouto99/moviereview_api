package mindswap.academy.moviereview_api.persistence.model.movie.actor;

import mindswap.academy.moviereview_api.command.movie.actor.ActorDto;

import java.util.ArrayList;
import java.util.List;


public class ActorPojo {
    // Models
    public static final Actor ACTOR_EXAMPLE = Actor.builder()
            .id(1L)
            .image("something")
            .name("something")
            .build();

    public static final ActorDto ACTOR_DTO_EXAMPLE = ActorDto.builder()
            .id(1L)
            .image("something")
            .name("something")
            .build();


    // Lists
    public static final List<Actor> ACTOR_LIST_EXAMPLE = new ArrayList<>(List.of(
            Actor.builder()
                    .id(1L)
                    .name("something")
                    .build()
    ));

    public static final List<ActorDto> ACTOR_DTO_LIST_EXAMPLE = new ArrayList<>(List.of(
            ActorDto.builder()
                    .id(1L)
                    .name("something")
                    .build()
    ));
}
