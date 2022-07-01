package mindswap.academy.moviereview_api.persistence.model.movie.director;

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
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @ManyToMany(mappedBy = "directorList")
    private List<Movie> movieList;
}
