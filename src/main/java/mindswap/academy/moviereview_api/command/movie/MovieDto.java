package mindswap.academy.moviereview_api.command.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MovieDto {
    @NotEmpty
    @Size(max = 100,message = "max title size 20 chars")
    private String title;
    @NotEmpty
    @Size(max  = 150,message = "max title size 40 chars")
    private String fullTitle;
    @NotEmpty
    @Pattern(regexp = "((https?:\\/\\/)?[^\\s.]+\\.[\\w][^\\s]+)", message = "You need a link")
    private String image;
    @NotEmpty
    @Pattern(regexp="[\\d]{4}", message = "only numbers ex: 2020")
    private String year;
    @NotEmpty
    @Pattern(regexp = "(?<YEAR>\\d{4})(?<SEPARATOR>[-.\\/])(?<MONTH>\\d\\d)(\\k<SEPARATOR>)(?<DAY>\\d?\\d)", message = "full release date ex: 2020-9-20")
    private String releaseDate;
    @NotEmpty
    @Pattern(regexp = "(0?[1-9]|1[0 12])h\\s([0-5]\\d)\\s?(min)", message = "Full time of the movie")
    private String runtimeStr;
    @NotEmpty
    @Pattern(regexp ="(?:PG-13)?|(?:R)?|(?:NC-17)?|(?:G)?|(?:PG)?")
    private String contentRating;
    @NotNull
    private List<IdDto> actorList;
    @NotNull
    private List<IdDto> directorList;
    @NotNull
    private List<IdDto> writerList;
    @NotNull
    private List<IdDto> genreList;

}
