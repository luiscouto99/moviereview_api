package mindswap.academy.moviereview_api.controller.user;

import lombok.AllArgsConstructor;
import mindswap.academy.moviereview_api.command.movie.MovieDto;
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

    @GetMapping("/search")
    public List<UserDto> searchUsers(@RequestParam(value = "roleid", required = false) Long roleId,
                                     @RequestParam(value = "firstname", required = false) String firstName,
                                     @RequestParam(value = "lastname", required = false) String lastName,
                                     @RequestParam(value = "email", required = false) String email) {
        return this.USER_SERVICE.search(roleId, firstName, lastName, email);
    }

    @GetMapping("/favourite/{id}")
    public List<MovieDto> getFavouriteList(@PathVariable("id") Long userId) {
        return this.USER_SERVICE.getFavouriteList(userId);
    }

    @PostMapping
    public UserDto addUser(@Valid @RequestBody UserDto userDto) {
        return this.USER_SERVICE.add(userDto);
    }

    @PostMapping("/favourite")
    public ResponseEntity<Object> addMovieToFavourite(@RequestParam(value = "userid") Long userId,
                                                      @RequestParam(value = "movieid") Long movieId) {
        return this.USER_SERVICE.addMovie(userId, movieId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long id) {
        return this.USER_SERVICE.delete(id);
    }

    @DeleteMapping("/favourite")
    public ResponseEntity<Object> deleteMovieFromFavourite(@RequestParam(value = "userid") Long userId,
                                                           @RequestParam(value = "movieid") Long movieId) {
        return this.USER_SERVICE.deleteMovie(userId, movieId);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable("id") Long id,
                              @Valid @RequestBody UserUpdateDto userUpdateDto) {
        return this.USER_SERVICE.update(id, userUpdateDto);
    }

    @DeleteMapping("/clearcache")
    public void clearCache() {
        this.USER_SERVICE.clearCache();
    }
}
