package mindswap.academy.moviereview_api.persistence.model.user;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Builder
@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
//@Entity
@EqualsAndHashCode
@Table(name = "users")
public class User {
}
