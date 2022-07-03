package mindswap.academy.moviereview_api.persistence.model.user.role;

import mindswap.academy.moviereview_api.persistence.model.user.role.Role;

public class RolePojo {
    public static final Role ROLE_EXAMPLE = Role.builder()
            .id(1L)
            .roleName("User")
            .build();
}
