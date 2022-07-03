package mindswap.academy.moviereview_api.command.movie.writer;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class WriterUpdateDto {
    @NotEmpty
    private String name;
}
