package mindswap.academy.moviereview_api.command.review;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ReviewDto {
    private Long id;

    @NotNull(message = "userId can't be empty")
    private Long userId;

    @NotNull(message = "moviedId can't be empty")
    private Long movieId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int totalReviews;

    @NotEmpty
    @Size(min = 15, message = "A review minimun amount of characters is 15!")
    private String review;

    @NotNull
    @Min(value = 1, message = "The ratingId has to be a number between 1-5")
    @Max(value = 5, message = "The ratingId has to be a number between 1-5")
    private Long ratingId;
}
