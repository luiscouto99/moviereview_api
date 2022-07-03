package mindswap.academy.moviereview_api.command.user.role;

import lombok.*;
import mindswap.academy.moviereview_api.persistence.model.user.User;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RoleUpdateDto {
    @Size(min = 1, max = 20)
    private String roleName;
}
