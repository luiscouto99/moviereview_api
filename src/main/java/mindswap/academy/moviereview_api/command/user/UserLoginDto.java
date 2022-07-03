package mindswap.academy.moviereview_api.command.user;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {
    private String email;
    private String password;
    public String getUsername(){
        return email;
    }
}
