package mindswap.academy.moviereview_api.persistence.model.movie;

import mindswap.academy.moviereview_api.command.movie.IdDto;
import mindswap.academy.moviereview_api.command.movie.MovieDto;
import mindswap.academy.moviereview_api.command.movie.MovieUpdateDto;
import mindswap.academy.moviereview_api.command.movie.OutMovieDto;
import mindswap.academy.moviereview_api.command.movie.writer.WriterDto;
import mindswap.academy.moviereview_api.persistence.model.movie.writer.Writer;

import java.util.ArrayList;
import java.util.List;

import static mindswap.academy.moviereview_api.persistence.model.movie.actor.ActorPojo.*;
import static mindswap.academy.moviereview_api.persistence.model.movie.director.DirectorPojo.*;
import static mindswap.academy.moviereview_api.persistence.model.movie.genre.GenrePojo.*;
import static mindswap.academy.moviereview_api.persistence.model.movie.writer.WriterPojo.*;

public class MoviePojo {
    public static final List<IdDto> LIST_EXAMPLE = new ArrayList<>(List.of(
            IdDto.builder()
                    .id(1L)
                    .build()
    ));

    // Models
    public static final Movie MOVIE_EXAMPLE = Movie.builder()
            .id(1L)
            .title("something")
            .fullTitle("something")
            .year("something")
            .releaseDate("something")
            .runtimeStr("something")
            .image("something")
            .totalReviews(1)
            .actorList(ACTOR_LIST_EXAMPLE)
            .directorList(DIRECTOR_LIST_EXAMPLE)
            .genreList(GENRE_LIST_EXAMPLE)
            .writerList(WRITER_LIST_EXAMPLE)
            .contentRating("something")
            .build();

    public static final MovieDto MOVIE_DTO_EXAMPLE = MovieDto.builder()
            .title("something")
            .fullTitle("something")
            .year("something")
            .releaseDate("something")
            .runtimeStr("something")
            .image("something")
            .actorList(LIST_EXAMPLE)
            .directorList(LIST_EXAMPLE)
            .genreList(LIST_EXAMPLE)
            .writerList(LIST_EXAMPLE)
            .contentRating("something")
            .build();

    public static final OutMovieDto OUT_MOVIE_DTO_EXAMPLE = OutMovieDto.builder()
            .id(1L)
            .title("something")
            .fullTitle("something")
            .year("something")
            .releaseDate("something")
            .runtimeStr("something")
            .image("something")
            .totalReviews(1)
            .actorList(ACTOR_DTO_LIST_EXAMPLE)
            .directorList(DIRECTOR_DTO_LIST_EXAMPLE)
            .genreList(GENRE_DTO_LIST_EXAMPLE)
            .writerList(WRITER_DTO_LIST_EXAMPLE)
            .contentRating("something")
            .build();


    // Lists
    public static final List<Movie> MOVIE_LIST_EXAMPLE = new ArrayList<>(List.of(
            Movie.builder()
                    .id(1L)
                    .title("something")
                    .fullTitle("something")
                    .year("something")
                    .releaseDate("something")
                    .runtimeStr("something")
                    .image("something")
                    .totalReviews(1)
                    .actorList(ACTOR_LIST_EXAMPLE)
                    .directorList(DIRECTOR_LIST_EXAMPLE)
                    .genreList(GENRE_LIST_EXAMPLE)
                    .writerList(WRITER_LIST_EXAMPLE)
                    .contentRating("something")
                    .build()
    ));

    public static final List<MovieDto> MOVIE_DTO_LIST_EXAMPLE = new ArrayList<>(List.of(
            MovieDto.builder()
                    .title("something")
                    .fullTitle("something")
                    .year("something")
                    .releaseDate("something")
                    .runtimeStr("something")
                    .image("something")
                    .actorList(LIST_EXAMPLE)
                    .directorList(LIST_EXAMPLE)
                    .genreList(LIST_EXAMPLE)
                    .writerList(LIST_EXAMPLE)
                    .contentRating("something")
                    .build()
    ));

    public static final List<OutMovieDto> OUT_MOVIE_DTO_LIST_EXAMPLE = new ArrayList<>(List.of(
            OutMovieDto.builder()
                    .id(1L)
                    .title("something")
                    .fullTitle("something")
                    .year("something")
                    .releaseDate("something")
                    .runtimeStr("something")
                    .image("something")
                    .totalReviews(1)
                    .actorList(ACTOR_DTO_LIST_EXAMPLE)
                    .directorList(DIRECTOR_DTO_LIST_EXAMPLE)
                    .genreList(GENRE_DTO_LIST_EXAMPLE)
                    .writerList(WRITER_DTO_LIST_EXAMPLE)
                    .contentRating("something")
                    .build()
    ));
    public static final MovieUpdateDto UPDATE_MOVIE_DTO_EXAMPLE = MovieUpdateDto.builder()
            .title("something")
            .fullTitle("something")
            .year("something")
            .releaseDate("something")
            .runtimeStr("something")
            .image("something")
            .actorList(LIST_EXAMPLE)
            .directorList(LIST_EXAMPLE)
            .genreList(LIST_EXAMPLE)
            .writerList(LIST_EXAMPLE)
            .contentRating("something")
            .build();
}
