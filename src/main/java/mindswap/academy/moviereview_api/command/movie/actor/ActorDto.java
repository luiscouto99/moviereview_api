package mindswap.academy.moviereview_api.command.movie.actor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActorDto implements Serializable {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    private String image;
    private String name;
}
