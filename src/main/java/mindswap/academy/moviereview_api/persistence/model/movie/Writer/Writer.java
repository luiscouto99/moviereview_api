package mindswap.academy.moviereview_api.persistence.model.movie.Writer;

import lombok.*;
import mindswap.academy.moviereview_api.dataloader.Actors;
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
public class Writer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "writerList")
    private List<Movie> movieList;
}
