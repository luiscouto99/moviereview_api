package mindswap.academy.moviereview_api.persistence.model.user;

import mindswap.academy.moviereview_api.command.user.UserDto;

import java.time.LocalDate;

import static mindswap.academy.moviereview_api.persistence.model.review.ReviewPojo.REVIEW_LIST_EXAMPLE;
import static mindswap.academy.moviereview_api.persistence.model.user.role.RolePojo.ROLE_EXAMPLE;

public class UserPojo {
    public static final User USER_EXAMPLE = User.builder()
            .id(1L)
            .roleId(ROLE_EXAMPLE)
            .reviewList(REVIEW_LIST_EXAMPLE)
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
