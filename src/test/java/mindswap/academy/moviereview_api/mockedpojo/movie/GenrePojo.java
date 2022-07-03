package mindswap.academy.moviereview_api.mockedpojo.movie;

import mindswap.academy.moviereview_api.command.movie.genre.GenreDto;
import mindswap.academy.moviereview_api.persistence.model.movie.genre.Genre;

import java.util.ArrayList;
import java.util.List;

public class GenrePojo {
    // Models
    public static final Genre GENRE_EXAMPLE = Genre.builder()
            .id(1L)
            .value("something")
            .build();

    public static final GenreDto GENRE_DTO_EXAMPLE = GenreDto.builder()
            .id(1L)
            .value("something")
            .build();


    // Lists
    public static final List<Genre> GENRE_LIST_EXAMPLE = new ArrayList<>(List.of(
            Genre.builder()
                    .id(1L)
                    .value("something")
                    .build()
    ));

    public static final List<GenreDto> GENRE_DTO_LIST_EXAMPLE = new ArrayList<>(List.of(
            GenreDto.builder()
                    .id(1L)
                    .value("something")
                    .build()
    ));
}
