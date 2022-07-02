package mindswap.academy.moviereview_api.command.movie;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieUpdateDto {
    @Size(max = 100,message = "max title size 20 chars")
    private String title;
    @Size(max  = 150,message = "max title size 40 chars")
    private String fullTitle;
    @Pattern(regexp = "((https?:\\/\\/)?[^\\s.]+\\.[\\w][^\\s]+)", message = "You need a link")
    private String image;
    @Pattern(regexp="[\\d]{4}", message = "only numbers ex: 2020")
    private String year;
    @Pattern(regexp = "(?<YEAR>\\d{4})(?<SEPARATOR>[-.\\/])(?<MONTH>\\d\\d)(\\k<SEPARATOR>)(?<DAY>\\d?\\d)", message = "full release date ex: 2020-9-20")
    private String releaseDate;
    @Pattern(regexp = "(0?[1-9]|1[0 12])h\\s([0-5]\\d)\\s?(min)", message = "Full time of the movie")
    private String runtimeStr;
    @Pattern(regexp ="(?:PG-13)?|(?:R)?|(?:NC-17)?|(?:G)?|(?:PG)?")
    private String contentRating;
    private List<IdDto> actorList;
    private List<IdDto> directorList;
    private List<IdDto> writerList;
    private List<IdDto> genreList;
}
