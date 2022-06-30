package mindswap.academy.moviereview_api.controller.user.role;

import lombok.AllArgsConstructor;
import mindswap.academy.moviereview_api.command.user.role.RoleDto;
import mindswap.academy.moviereview_api.persistence.model.user.role.Role;
import mindswap.academy.moviereview_api.persistence.repository.user.role.IRoleRepository;
import mindswap.academy.moviereview_api.service.user.role.IRoleService;
import mindswap.academy.moviereview_api.service.user.role.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/users/roles")
@AllArgsConstructor
public class RoleController {
    private final IRoleService SERVICE;

    @GetMapping
    public List<RoleDto> getRoles() {
        return this.SERVICE.getAll();
    }

    @PostMapping
    public RoleDto addRole(@Valid @RequestBody RoleDto roleDto) {
        return this.SERVICE.add(roleDto);
    }

    @DeleteMapping("{id}")
    public
}
