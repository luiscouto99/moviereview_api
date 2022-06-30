package mindswap.academy.moviereview_api.command.movie.genre;

import lombok.*;

import javax.validation.constraints.NotEmpty;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenreUpdateDto {
    @NotEmpty
    private String value;
}
