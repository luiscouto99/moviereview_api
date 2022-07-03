package mindswap.academy.moviereview_api.service.user.role;

import lombok.AllArgsConstructor;
import mindswap.academy.moviereview_api.command.user.role.RoleDto;
import mindswap.academy.moviereview_api.command.user.role.RoleUpdateDto;
import mindswap.academy.moviereview_api.converter.user.role.IRoleConverter;
import mindswap.academy.moviereview_api.exception.ConflictException;
import mindswap.academy.moviereview_api.exception.NotFoundException;
import mindswap.academy.moviereview_api.persistence.model.user.role.Role;
import mindswap.academy.moviereview_api.persistence.repository.user.role.IRoleRepository;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static mindswap.academy.moviereview_api.exception.ExceptionMessages.*;

@Service
@AllArgsConstructor
public class RoleService implements IRoleService {
    private final IRoleRepository roleRepository;
    private final IRoleConverter roleConverter;
    private final CacheManager cacheManager;

    @Override
    @Cacheable("roles")
    public List<RoleDto> getAll() {
        return this.roleConverter.converterList(
                this.roleRepository.findAll(), RoleDto.class);
    }

    @Override
    public RoleDto add(RoleDto roleDto) {
        this.roleRepository.findByroleName(roleDto.getRoleName())
                .ifPresent(role -> {
                    throw new ConflictException(ROLE_ALREADY_EXISTS);
                });

        clearRoleCache();
        Role role = this.roleConverter.converter(roleDto, Role.class);
        return this.roleConverter.converter(
                this.roleRepository.save(role), RoleDto.class);
    }

    @Override
    @CacheEvict(key = "#id", value = "role")
    public ResponseEntity<Object> delete(Long id) {
        Optional<Role> role = this.roleRepository.findById(id);
        if (role.isEmpty()) return new ResponseEntity<>(ROLE_NOT_FOUND, HttpStatus.NOT_FOUND);

        clearRoleCache();
        this.roleRepository.deleteById(id);
        return new ResponseEntity<>("Role deleted", HttpStatus.OK);
    }

    @Override
    @CacheEvict(key = "#id", value = "role")
    public RoleDto update(Long id, RoleUpdateDto roleUpdateDto) {
        this.roleRepository.findByroleName(roleUpdateDto.getRoleName())
                .ifPresent(role -> {
                    throw new ConflictException(ROLE_ALREADY_EXISTS);
                });

        Role role = this.roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ROLE_NOT_FOUND));
        Role updatedRole = this.roleConverter.converterUpdate(roleUpdateDto, role);

        clearRoleCache();
        return this.roleConverter.converter(
                this.roleRepository.save(updatedRole), RoleDto.class);
    }

    private void clearRoleCache() {
        Cache roleCache = this.cacheManager.getCache("roles");
        if(roleCache!=null)roleCache.clear();
    }
}
