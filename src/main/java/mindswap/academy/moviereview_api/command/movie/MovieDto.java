package mindswap.academy.moviereview_api.command.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto implements Serializable {
    private Long id;
    private String title;
    private String fullTitle;
    private String image;
    private String year;
    private String releaseDate;
    private String runtimeStr;
    private List<IdDto> actorList;
    private List<IdDto> directorList;
    private List<IdDto> writerList;
    private List<IdDto> genreList;
    private String contentRating;
}
