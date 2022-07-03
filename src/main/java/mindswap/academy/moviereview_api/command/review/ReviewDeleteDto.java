package mindswap.academy.moviereview_api.command.review;

import lombok.*;

import javax.validation.constraints.NotNull;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ReviewDeleteDto {
    @NotNull
    private Long id;
    @NotNull
    private Long userId;
}
