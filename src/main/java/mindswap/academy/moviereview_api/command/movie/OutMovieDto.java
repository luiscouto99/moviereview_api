package mindswap.academy.moviereview_api.command.movie;

import lombok.*;
import mindswap.academy.moviereview_api.command.movie.director.DirectorDto;
import mindswap.academy.moviereview_api.command.movie.genre.GenreDto;
import mindswap.academy.moviereview_api.command.movie.writer.WriterDto;
import mindswap.academy.moviereview_api.command.movie.actor.ActorDto;
import mindswap.academy.moviereview_api.command.review.rating.RatingDto;
import mindswap.academy.moviereview_api.persistence.model.review.rating.Rating;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OutMovieDto implements Serializable {
    private Long id;
    private String title;
    private String fullTitle;
    private String image;
    private String year;
    private String releaseDate;
    private String runtimeStr;
    private String contentRating;
    private List<ActorDto> actorList;
    private List<DirectorDto> directorList;
    private List<WriterDto> writerList;
    private List<GenreDto> genreList;
    private RatingDto ratingId;
    private int totalReviews;
}
