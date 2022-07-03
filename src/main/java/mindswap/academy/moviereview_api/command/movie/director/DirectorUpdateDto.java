package mindswap.academy.moviereview_api.command.movie.director;

import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DirectorUpdateDto {
    @NotEmpty
    @Size(max = 50,message = " max size 50")
    private String name;
}
