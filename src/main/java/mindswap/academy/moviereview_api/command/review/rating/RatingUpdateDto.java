package mindswap.academy.moviereview_api.command.review.rating;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RatingUpdateDto {

    @NotEmpty
    private String rate;
}

