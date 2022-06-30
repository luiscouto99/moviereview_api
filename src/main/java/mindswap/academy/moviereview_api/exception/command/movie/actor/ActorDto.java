package mindswap.academy.moviereview_api.exception.command.movie.actor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActorDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    private String image;
    private String name;
    private String asCharacter;
}
