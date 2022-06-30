package mindswap.academy.moviereview_api.exception.command.movie.writer;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class WriterUpdateDto {
    @NotEmpty
    private String name;
}
