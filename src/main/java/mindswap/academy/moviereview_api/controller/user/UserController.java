package mindswap.academy.moviereview_api.controller.user;

import lombok.AllArgsConstructor;
import mindswap.academy.moviereview_api.command.user.UserDto;
import mindswap.academy.moviereview_api.command.user.UserUpdateDto;
import mindswap.academy.moviereview_api.service.user.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {
    private final IUserService USER_SERVICE;

    @GetMapping
    public List<UserDto> getUsers() {
        return this.USER_SERVICE.getAll();
    }

    @PostMapping
    public UserDto add(UserDto userDto) {
        return this.USER_SERVICE.add(userDto);
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(Long id) {
        return this.USER_SERVICE.delete(id);
    }

    @PutMapping
    public UserDto update(Long id, UserUpdateDto userUpdateDto) {
        return this.USER_SERVICE.update(id, userUpdateDto);
    }
}
