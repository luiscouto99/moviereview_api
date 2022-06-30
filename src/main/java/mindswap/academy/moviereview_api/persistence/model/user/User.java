package mindswap.academy.moviereview_api.persistence.model.user;

import lombok.*;
import mindswap.academy.moviereview_api.persistence.model.review.Review;
import mindswap.academy.moviereview_api.persistence.model.user.role.Role;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id_fk", referencedColumnName = "id")
    private Role roleId;

    @OneToMany(mappedBy = "userId")
    private List<Review> reviewList;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private LocalDate dateOfBirth;
    private LocalDate dateOfAccountCreation;

    @Column(nullable = false)
    private String password;
}
