package mindswap.academy.moviereview_api.persistence.model.user;

import mindswap.academy.moviereview_api.command.user.UserDto;
import mindswap.academy.moviereview_api.converter.user.IUserConverter;
import mindswap.academy.moviereview_api.converter.user.UserConverter;
import mindswap.academy.moviereview_api.persistence.repository.user.IUserRepository;
import mindswap.academy.moviereview_api.persistence.repository.user.role.IRoleRepository;
import mindswap.academy.moviereview_api.service.user.IUserService;
import mindswap.academy.moviereview_api.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static mindswap.academy.moviereview_api.mockedpojo.UserMockedPojo.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserTest {
    @Mock
    IUserRepository userRepository;
    IUserService userService;
    @Mock
    IRoleRepository roleRepository;

    @BeforeEach
    public void setup() {
        this.userService = new UserService(userRepository, new UserConverter(new ModelMapper()), roleRepository);
    }

    @Nested
    class getUser {

        @Test
        void testGetUserById() {
            when(userRepository.findById(1L))
                    .thenReturn(Optional.of(USER_EXAMPLE));

            UserDto result = userService.getUser(1L);

            assertEquals(USER_DTO_EXAMPLE, result);
        }
    }

    @Test
    void testAddUser() {
        when(roleRepository.findById(1L))
                .thenReturn(Optional.ofNullable(ROLE_EXAMPLE));
        when(userRepository.save(USER_EXAMPLE))
                .thenReturn(USER_EXAMPLE);

        UserDto result = userService.add(USER_DTO_EXAMPLE);

        assertEquals(USER_DTO_EXAMPLE, result);
    }
}
