package mindswap.academy.moviereview_api.command.movie.actor;

import lombok.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActorUpdateDto {
    @Pattern(regexp = "((https?:\\/\\/)?[^\\s.]+\\.[\\w][^\\s]+)", message = "You need a link")
    private String image;
    @Size(max = 50,message = " max size 50")
    private String name;
}
