package mindswap.academy.moviereview_api.service.user;

import mindswap.academy.moviereview_api.command.user.UserDto;
import mindswap.academy.moviereview_api.converter.user.UserConverter;
import mindswap.academy.moviereview_api.persistence.repository.user.IUserRepository;
import mindswap.academy.moviereview_api.persistence.repository.user.role.IRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static mindswap.academy.moviereview_api.mockedpojo.UserMockedPojo.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    IUserRepository iUserRepository;
    IUserService iUserService;
    @Mock
    IRoleRepository iRoleRepository;

    @BeforeEach
    public void setup() {
        this.iUserService = new UserService(iUserRepository,
                new UserConverter(new ModelMapper()),
                iRoleRepository
        );
    }

    @Nested
    class getUser {

        @Test
        void testGetUserById() {
            when(iUserRepository.findById(1L))
                    .thenReturn(Optional.of(USER_EXAMPLE));

            UserDto result = iUserService.getUser(1L);

            assertEquals(USER_DTO_EXAMPLE, result);
        }
    }

    @Test
    void testAddUser() {
        when(iRoleRepository.findById(1L))
                .thenReturn(Optional.ofNullable(ROLE_EXAMPLE));
        when(iUserRepository.save(USER_EXAMPLE))
                .thenReturn(USER_EXAMPLE);

        UserDto result = iUserService.add(USER_DTO_EXAMPLE);

        assertEquals(USER_DTO_EXAMPLE, result);
    }
}
