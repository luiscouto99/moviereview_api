package mindswap.academy.moviereview_api.persistence.model.review;

import lombok.*;
import mindswap.academy.moviereview_api.persistence.model.review.rating.Rating;

import javax.persistence.*;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String review;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "rating_id_fk")
    private Rating ratingId;

    //@ManyToOne(cascade = CascadeType.MERGE)
    //@JoinColumn(name = "movie_id_fk")
    //private Movie movieId;

    //@ManyToOne(cascade = CascadeType.MERGE)
    //@JoinColumn(name = "user_id_fk")
    //private User userId;
}