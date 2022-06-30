package mindswap.academy.moviereview_api.command.review;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewDto {
    private Long id;

    @NotEmpty
    @Size(min = 15, message = "The minimun amount of characters is 15!")
    private String review;

    @NotNull
    private Long ratingId;
}
