package mindswap.academy.moviereview_api.command.movie;

import lombok.*;
import mindswap.academy.moviereview_api.dataloader.movieloader.Actors;
import mindswap.academy.moviereview_api.dataloader.movieloader.Directors;
import mindswap.academy.moviereview_api.dataloader.movieloader.Genres;
import mindswap.academy.moviereview_api.dataloader.movieloader.Writers;

import java.util.List;
@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieApiDto {
    private String title;
    private String fullTitle;
    private String image;
    private String year;
    private String releaseDate;
    private  String runtimeStr;
    private List<Actors> actorList;
    private List<Directors> directorList;
    private List<Writers> writerList;
    private  List<Genres> genreList;
    private String contentRating;
}
