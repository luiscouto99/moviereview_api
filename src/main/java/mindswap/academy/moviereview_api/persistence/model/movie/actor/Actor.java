package mindswap.academy.moviereview_api.persistence.model.movie.actor;

import lombok.*;
import mindswap.academy.moviereview_api.persistence.model.movie.Movie;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;
    private String image;
    @Column(nullable = false, unique = true)
    private String name;
    private String asCharacter;
    @ManyToMany(mappedBy = "actorList")
    private List<Movie> movieList;
}