package mindswap.academy.moviereview_api.mockedpojo.movie;

import mindswap.academy.moviereview_api.command.movie.director.DirectorDto;
import mindswap.academy.moviereview_api.persistence.model.movie.director.Director;

import java.util.ArrayList;
import java.util.List;

public class DirectorPojo {
    // Models
    public static final Director DIRECTOR_EXAMPLE = Director.builder()
            .id(1L)
            .name("something")
            .build();

    public static final DirectorDto DIRECTOR_DTO_EXAMPLE = DirectorDto.builder()
            .id(1L)
            .name("something")
            .build();


    // Lists
    public static final List<Director> DIRECTOR_LIST_EXAMPLE = new ArrayList<>(List.of(
            Director.builder()
                    .id(1L)
                    .name("something")
                    .build()
    ));

    public static final List<DirectorDto> DIRECTOR_DTO_LIST_EXAMPLE = new ArrayList<>(List.of(
            DirectorDto.builder()
                    .id(1L)
                    .name("something")
                    .build()
    ));
}
