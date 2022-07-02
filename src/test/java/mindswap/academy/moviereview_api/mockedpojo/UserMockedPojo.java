package mindswap.academy.moviereview_api.mockedpojo;

import mindswap.academy.moviereview_api.command.user.UserDto;
import mindswap.academy.moviereview_api.persistence.model.user.User;
import mindswap.academy.moviereview_api.persistence.model.user.role.Role;

import java.time.LocalDate;
import java.util.List;

import static mindswap.academy.moviereview_api.mockedpojo.ReviewMockedPojo.REVIEW_EXAMPLE;

public class UserMockedPojo {

    public static final Role ROLE_EXAMPLE = Role.builder()
            .id(1L)
            .roleName("User")
            .build();

    public static final User USER_EXAMPLE = User.builder()
            .id(1L)
            .roleId(ROLE_EXAMPLE)
            .reviewList(List.of(REVIEW_EXAMPLE))
            .firstName("Nuno")
            .lastName("Carmo")
            .email("nuno.carmo@mindswap.academy")
            .dateOfBirth(LocalDate.parse("1800-12-12"))
            .password("123456789")
            .build();

    public static final UserDto USER_DTO_EXAMPLE = UserDto.builder()
            .id(1L)
            .roleId(1L)
            .firstName("Nuno")
            .lastName("Carmo")
            .email("nuno.carmo@mindswap.academy")
            .dateOfBirth(LocalDate.parse("1800-12-12"))
            .dateOfAccountCreation(LocalDate.now())
            .password("123456789")
            .build();
}
