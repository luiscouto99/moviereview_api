package mindswap.academy.moviereview_api.persistence.model.review.rating;

import lombok.*;
import mindswap.academy.moviereview_api.persistence.model.movie.Movie;
import mindswap.academy.moviereview_api.persistence.model.review.Review;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String rate;

    @OneToMany(mappedBy = "ratingId")
    private Set<Review> reviewList;

    @OneToMany(mappedBy = "ratingId")
    private List<Movie> movieList;
}