package mindswap.academy.moviereview_api.persistence.model.movie;

import lombok.*;
import mindswap.academy.moviereview_api.persistence.model.movie.writer.Writer;
import mindswap.academy.moviereview_api.persistence.model.movie.actor.Actor;
import mindswap.academy.moviereview_api.persistence.model.movie.director.Director;
import mindswap.academy.moviereview_api.persistence.model.movie.genre.Genre;
import mindswap.academy.moviereview_api.persistence.model.review.Review;
import mindswap.academy.moviereview_api.persistence.model.review.rating.Rating;
import mindswap.academy.moviereview_api.persistence.model.user.User;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false, unique = true, updatable = false)
    private Long id;
    @Column
    private String title;
    @Column
    private String fullTitle;
    @Column
    private String year;
    @Column
    private String releaseDate;
    @Column
    private String runtimeStr;
    @Column
    private String image;
    private int totalReviews;
    @OneToMany(mappedBy = "movieId",cascade = CascadeType.REMOVE)
    private List<Review> reviewList;

    @ManyToMany
    @JoinTable(
            name = "actor_movie_list",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actorList;

    @ManyToMany
    @JoinTable(
            name = "director_movie_list",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id")
    )
    private List<Director> directorList;

    @ManyToMany
    @JoinTable(
            name = "writer_movie_list",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "writer_id")
    )
    private List<Writer> writerList;

    @ManyToMany
    @JoinTable(
            name = "genre_movie_list",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genreList;

    @Column
    private String contentRating;

    @ManyToOne
    @JoinColumn(name = "rating_id_fk")
    private Rating ratingId;

    @ManyToMany(mappedBy = "movieList")
    private List<User> userList;
}
