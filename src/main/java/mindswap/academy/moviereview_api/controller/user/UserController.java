package mindswap.academy.moviereview_api.controller.user;

import lombok.AllArgsConstructor;
import mindswap.academy.moviereview_api.command.user.UserDto;
import mindswap.academy.moviereview_api.command.user.UserUpdateDto;
import mindswap.academy.moviereview_api.service.user.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") Long id) {
        return this.USER_SERVICE.getUser(id);
    }

    @PostMapping
    public UserDto addUser(@Valid @RequestBody UserDto userDto) {
        return this.USER_SERVICE.add(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long id) {
        return this.USER_SERVICE.delete(id);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable("id") Long id,
                              @Valid @RequestBody UserUpdateDto userUpdateDto) {
        return this.USER_SERVICE.update(id, userUpdateDto);
    }
}
