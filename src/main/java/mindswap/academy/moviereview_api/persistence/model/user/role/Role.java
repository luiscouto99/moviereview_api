package mindswap.academy.moviereview_api.persistence.model.user.role;

import lombok.*;
import mindswap.academy.moviereview_api.persistence.model.user.User;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String role;

    @OneToMany(mappedBy = "role")
    private List<User> users;
}
