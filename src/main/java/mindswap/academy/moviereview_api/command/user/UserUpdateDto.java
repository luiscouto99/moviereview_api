package mindswap.academy.moviereview_api.command.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {

    @Min(0)
    private Long roleId;

    @Size(max = 20)
    private String firstName;

    @Size(max = 20)
    private String lastName;

    @Email
    @Size(max = 40)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
