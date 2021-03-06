package mindswap.academy.moviereview_api.command.movie;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class IdDto implements Serializable {
    private Long id;
}
