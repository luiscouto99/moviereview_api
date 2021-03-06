package mindswap.academy.moviereview_api.command.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long roleId = 1L;

    @NotEmpty
    @Size(min = 1, max = 20)
    private String firstName;

    @NotEmpty
    @Size(min = 1, max = 20)
    private String lastName;

    @NotEmpty
    @Email
    @Size(max = 40)
    private String email;

    @NotNull
    @DateTimeFormat
    private LocalDate dateOfBirth;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate dateOfAccountCreation  = LocalDate.now();

    @NotEmpty
    @Size(min = 8, max = 30)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
