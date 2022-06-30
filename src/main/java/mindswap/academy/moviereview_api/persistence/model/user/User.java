package mindswap.academy.moviereview_api.persistence.model.user;

import lombok.*;
import mindswap.academy.moviereview_api.persistence.model.user.role.Role;

import javax.persistence.*;
import java.time.LocalDate;

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

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id_fk", referencedColumnName = "id")
    private Role roleId;

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
