package mindswap.academy.moviereview_api.command.review;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewUpdateDto {
    @NotEmpty
    private String review;

    @NotEmpty
    private Long rating;
}
