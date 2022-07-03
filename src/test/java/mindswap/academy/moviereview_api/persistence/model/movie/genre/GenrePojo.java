package mindswap.academy.moviereview_api.persistence.model.movie.genre;

import mindswap.academy.moviereview_api.command.movie.genre.GenreDto;
import mindswap.academy.moviereview_api.command.movie.genre.GenreUpdateDto;
import mindswap.academy.moviereview_api.persistence.model.movie.genre.Genre;

import java.util.ArrayList;
import java.util.List;

public class GenrePojo {
    public static final GenreUpdateDto GENRE_UPDATE_DTO_EXAMPLE = GenreUpdateDto.builder()
            .value("something")
            .build();
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
