package mindswap.academy.moviereview_api.command.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotEmpty
    private Long roleId;

    @NotEmpty
    @Size(min = 1, max = 20)
    private String firstName;

    @NotEmpty
    @Size(min = 1, max = 20)
    private String lastName;

    @NotNull
    @DateTimeFormat
    private LocalDate dateOfBirth;
    private LocalDate dateOfAccountCreation = LocalDate.now();

    @NotEmpty
    @Size(min = 8, max = 30)
    private String password;
}
