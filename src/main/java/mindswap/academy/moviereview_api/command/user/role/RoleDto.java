package mindswap.academy.moviereview_api.command.user.role;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto implements Serializable {
@EqualsAndHashCode
public class RoleDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotEmpty
    @Size(min = 1, max = 20)
    private String roleName;
}
