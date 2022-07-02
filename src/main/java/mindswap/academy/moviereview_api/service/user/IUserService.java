package mindswap.academy.moviereview_api.service.user;

import mindswap.academy.moviereview_api.command.user.UserDto;
import mindswap.academy.moviereview_api.command.user.UserUpdateDto;
import mindswap.academy.moviereview_api.persistence.model.user.User;
import mindswap.academy.moviereview_api.service.IGenericService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface IUserService extends IGenericService<UserDto, UserDto, UserUpdateDto> {
    UserDto getUser(Long id);
    List<UserDto> search(Long roleId, String firstName, String lastName, String email);
}
