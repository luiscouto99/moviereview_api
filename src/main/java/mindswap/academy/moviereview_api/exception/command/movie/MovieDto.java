package mindswap.academy.moviereview_api.exception.command.movie;

import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
    private Long id;
    private String title;
    private String fullTitle;
    private String year;
    private String releaseDate;
    private String runtimeStr;
    private List<IdDto> actorList;
    private List<IdDto> directorList;
    private List<IdDto> writerList;
    private List<IdDto> genreList;
//    private String contentRating;
}
