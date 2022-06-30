package mindswap.academy.moviereview_api.exception.command.movie.actor;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActorUpdateDto {
    private String image;
    private String name;
    private String asCharacter;
}
