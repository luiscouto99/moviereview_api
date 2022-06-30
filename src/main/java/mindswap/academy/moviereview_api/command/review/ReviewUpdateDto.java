package mindswap.academy.moviereview_api.command.review;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReviewUpdateDto {
    @NotEmpty
    private String review;

    @NotNull
    private Long ratingId;
}
