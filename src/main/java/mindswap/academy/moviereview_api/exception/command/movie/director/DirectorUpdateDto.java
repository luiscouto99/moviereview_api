package mindswap.academy.moviereview_api.exception.command.movie.director;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@Builder
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class DirectorUpdateDto {
    private String name;
}
