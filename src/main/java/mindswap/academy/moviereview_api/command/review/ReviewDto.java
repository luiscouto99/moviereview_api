package mindswap.academy.moviereview_api.command.review;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewDto {
    private Long id;

    @NotEmpty
    @Size(min = 15, message = "A review minimun amount of characters is 15!")
    private String review;

    @NotNull
    @Min(value = 1, message = "The ratingId has to be a number between 1-5")
    @Max(value = 5, message = "The ratingId has to be a number between 1-5")
    private Long ratingId;
}
