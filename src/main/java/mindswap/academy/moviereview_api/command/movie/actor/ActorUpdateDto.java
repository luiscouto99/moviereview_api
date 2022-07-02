package mindswap.academy.moviereview_api.command.movie.actor;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActorUpdateDto {
    private String image;
    private String name;
}
