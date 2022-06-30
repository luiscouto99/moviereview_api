package mindswap.academy.moviereview_api.persistence.model.movie.genre;

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
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;
    private String value;
    @ManyToMany(mappedBy = "genreList")
    private List<Movie> movieList;
}
