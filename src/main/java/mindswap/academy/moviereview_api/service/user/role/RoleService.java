package mindswap.academy.moviereview_api.service.user.role;

import lombok.AllArgsConstructor;
import mindswap.academy.moviereview_api.command.user.role.RoleDto;
import mindswap.academy.moviereview_api.command.user.role.RoleUpdateDto;
import mindswap.academy.moviereview_api.converter.user.role.IRoleConverter;
import mindswap.academy.moviereview_api.exceptions.RoleAlreadyExistsException;
import mindswap.academy.moviereview_api.exceptions.RoleNotFoundException;
import mindswap.academy.moviereview_api.persistence.model.user.role.Role;
import mindswap.academy.moviereview_api.persistence.repository.user.role.IRoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static mindswap.academy.moviereview_api.exceptions.ExceptionMessages.ROLE_ALREADY_EXISTS;
import static mindswap.academy.moviereview_api.exceptions.ExceptionMessages.ROLE_NOT_FOUND;

@Service
@AllArgsConstructor
public class RoleService implements IRoleService {
    private final IRoleRepository REPOSITORY;
    private final IRoleConverter CONVERTER;

    @Override
    public List<RoleDto> getAll() {
        return this.CONVERTER.converterList(
                this.REPOSITORY.findAll(), RoleDto.class);
    }

    @Override
    public RoleDto add(RoleDto roleDto) {
        this.REPOSITORY.findByroleName(roleDto.getRoleName())
                .ifPresent(role -> {throw new RoleAlreadyExistsException(ROLE_ALREADY_EXISTS);});
        Role role = this.CONVERTER.converter(roleDto, Role.class);
        return this.CONVERTER.converter(
                this.REPOSITORY.save(role), RoleDto.class);
    }

    @Override
    public ResponseEntity<Object> delete(Long id) {
        Optional<Role> role = this.REPOSITORY.findById(id);
        if (role.isEmpty()) return new ResponseEntity<>(ROLE_NOT_FOUND, HttpStatus.NOT_FOUND);
        this.REPOSITORY.deleteById(id);
        return new ResponseEntity<>("Role deleted", HttpStatus.OK);
    }

    @Override
    public RoleDto update(Long id, RoleUpdateDto roleUpdateDto) {
        this.REPOSITORY.findByroleName(roleUpdateDto.getRoleName())
                .ifPresent(role -> {throw new RoleAlreadyExistsException(ROLE_ALREADY_EXISTS);});
        Role role = this.REPOSITORY.findById(id)
                .orElseThrow(() -> new RoleNotFoundException(ROLE_NOT_FOUND));

        Role updatedRole = this.CONVERTER.converterUpdate(roleUpdateDto, role);
        return this.CONVERTER.converter(
                this.REPOSITORY.save(updatedRole), RoleDto.class);
    }
}
