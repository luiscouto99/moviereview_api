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
import java.util.Objects;
import java.util.Optional;

import static mindswap.academy.moviereview_api.exception.ExceptionMessages.*;

@Service
@AllArgsConstructor
public class RoleService implements IRoleService {
    private final IRoleRepository REPOSITORY;
    private final IRoleConverter CONVERTER;
    private final CacheManager CACHE_MANAGER;

    @Override
    @Cacheable("roles")
    public List<RoleDto> getAll() {
        return this.CONVERTER.converterList(
                this.REPOSITORY.findAll(), RoleDto.class);
    }

    @Override
    public RoleDto add(RoleDto roleDto) {
        this.REPOSITORY.findByroleName(roleDto.getRoleName())
                .ifPresent(role -> {
                    throw new ConflictException(ROLE_ALREADY_EXISTS);
                });

        clearRoleCache();
        Role role = this.CONVERTER.converter(roleDto, Role.class);
        return this.CONVERTER.converter(
                this.REPOSITORY.save(role), RoleDto.class);
    }

    @Override
    @CacheEvict(key = "#id", value = "role")
    public ResponseEntity<Object> delete(Long id) {
        Optional<Role> role = this.REPOSITORY.findById(id);
        if (role.isEmpty()) return new ResponseEntity<>(ROLE_NOT_FOUND, HttpStatus.NOT_FOUND);

        clearRoleCache();
        this.REPOSITORY.deleteById(id);
        return new ResponseEntity<>("Role deleted", HttpStatus.OK);
    }

    @Override
    @CacheEvict(key = "#id", value = "role")
    public RoleDto update(Long id, RoleUpdateDto roleUpdateDto) {
        this.REPOSITORY.findByroleName(roleUpdateDto.getRoleName())
                .ifPresent(role -> {
                    throw new ConflictException(ROLE_ALREADY_EXISTS);
                });

        Role role = this.REPOSITORY.findById(id)
                .orElseThrow(() -> new NotFoundException(ROLE_NOT_FOUND));
        Role updatedRole = this.CONVERTER.converterUpdate(roleUpdateDto, role);

        clearRoleCache();
        return this.CONVERTER.converter(
                this.REPOSITORY.save(updatedRole), RoleDto.class);
    }

    private void clearRoleCache() {
        Cache roleCache = this.CACHE_MANAGER.getCache("roles");
        if(roleCache!=null)roleCache.clear();
    }
}
