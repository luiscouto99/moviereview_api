package mindswap.academy.moviereview_api.exception.command.movie;

import lombok.*;
import mindswap.academy.moviereview_api.exception.command.movie.actor.ActorDto;
import mindswap.academy.moviereview_api.exception.command.movie.director.DirectorDto;
import mindswap.academy.moviereview_api.exception.command.movie.genre.GenreDto;
import mindswap.academy.moviereview_api.exception.command.movie.writer.WriterDto;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutMovieDto {
    private Long id;
    private String title;
    private String fullTitle;
    private String year;
    private String releaseDate;
    private String runtimeStr;
    private List<ActorDto> actorList;
    private List<DirectorDto> directorList;
    private List<WriterDto> writerList;
    private List<GenreDto> genreList;
}
