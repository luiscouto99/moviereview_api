package mindswap.academy.moviereview_api.controller.user.role;

import lombok.AllArgsConstructor;
import mindswap.academy.moviereview_api.command.user.role.RoleDto;
import mindswap.academy.moviereview_api.command.user.role.RoleUpdateDto;
import mindswap.academy.moviereview_api.service.user.role.IRoleService;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/users/roles")
@AllArgsConstructor
public class RoleController {
    private final IRoleService ROLE_SERVICE;

    @GetMapping
    public List<RoleDto> getRoles() {
        return this.ROLE_SERVICE.getAll();
    }

    @PostMapping
    public RoleDto addRole(@Valid @RequestBody RoleDto roleDto) {
        return this.ROLE_SERVICE.add(roleDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long id) {
        return this.ROLE_SERVICE.delete(id);
    }

    @PutMapping("{id}")
    public RoleDto updateRole(@PathVariable("id") Long id,
                              @Valid @RequestBody RoleUpdateDto roleUpdateDto) {
        return this.ROLE_SERVICE.update(id, roleUpdateDto);
    }
}
