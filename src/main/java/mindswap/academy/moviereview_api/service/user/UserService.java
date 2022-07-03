package mindswap.academy.moviereview_api.service.user;

import lombok.AllArgsConstructor;
import mindswap.academy.moviereview_api.command.movie.MovieDto;
import mindswap.academy.moviereview_api.command.user.UserAuthDto;
import mindswap.academy.moviereview_api.command.user.UserDto;
import mindswap.academy.moviereview_api.command.user.UserUpdateDto;
import mindswap.academy.moviereview_api.config.CheckAuth;
import mindswap.academy.moviereview_api.converter.user.IUserConverter;
import mindswap.academy.moviereview_api.exception.*;
import mindswap.academy.moviereview_api.persistence.model.movie.Movie;
import mindswap.academy.moviereview_api.persistence.model.user.User;
import mindswap.academy.moviereview_api.persistence.model.user.role.Role;
import mindswap.academy.moviereview_api.persistence.repository.movie.IMovieRepository;
import mindswap.academy.moviereview_api.persistence.repository.user.IUserRepository;
import mindswap.academy.moviereview_api.persistence.repository.user.role.IRoleRepository;
import nonapi.io.github.classgraph.json.JSONUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static mindswap.academy.moviereview_api.exception.ExceptionMessages.*;

@Service
@AllArgsConstructor
public class UserService implements IUserService, UserDetailsService {
    private final IUserRepository userRepository;
    private final IUserConverter userConverter;
    private final IRoleRepository roleRepository;
    private final IMovieRepository movieRepository;
    private final CacheManager cacheManager;
    private final PasswordEncoder encoder;
    private final CheckAuth checkAuth;

    @Override
    @Cacheable("users")
    public List<UserDto> getAll() {
        return this.userConverter.converterList(
                this.userRepository.findAll(), UserDto.class);
    }

    @Override
    @Cacheable(key = "#id", value = "user")
    public UserDto getUser(Long id) {
        checkAuth.checkIfUserEqualsIdGiven(id);
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return this.userConverter.converter(user, UserDto.class);
    }

    @Override
    @Cacheable("users")
    public List<UserDto> search(Long roleId, String firstName, String lastName, String email) {
        if (roleId == null && firstName.equals("") && lastName.equals("") && email.equals(""))
            throw new BadRequestException(AT_LEAST_1_PARAMETER);

        return this.userConverter.converterList(
                this.userRepository.search(roleId, firstName, lastName, email), UserDto.class);
    }

    @Override
    @Cacheable("users")
    public List<MovieDto> getFavouriteList(Long userId) {
        checkAuth.checkIfUserEqualsIdGiven(userId);
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return this.userConverter.converterList(user.getMovieList(), MovieDto.class);
    }

    @Override
    public UserDto add(UserDto userDto) {
        this.roleRepository.findById(userDto.getRoleId())
                .orElseThrow(() -> new NotFoundException(ROLE_NOT_FOUND));
        this.userRepository.findByEmail(userDto.getEmail())
                .ifPresent(user -> {
                    throw new ConflictException(EMAIL_REGISTERED);
                });

        clearUserCache();
        User user = this.userConverter.converter(userDto, User.class);
        user.setPassword(encoder.encode(user.getPassword()));
        this.userRepository.save(user);
        return this.userConverter.converter(user, UserDto.class);
    }

    @Override
    public ResponseEntity<Object> addMovie(Long userId, Long movieId) {
        checkAuth.checkIfUserEqualsIdGiven(userId);
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        Movie movie = this.movieRepository.findById(movieId)
                .orElseThrow(() -> new NotFoundException(MOVIE_NOT_FOUND));

        if (user.getMovieList().contains(movie))
            return new ResponseEntity<>("Movie is already on the favourite list", HttpStatus.CONFLICT);

        clearUserCache();
        user.addMovie(movie);
        this.userRepository.save(user);
        return new ResponseEntity<>("Movie added to the favourite list", HttpStatus.OK);
    }

    @Override
    @CacheEvict(key = "#id", value = "user")
    public ResponseEntity<Object> delete(Long id) {
        checkAuth.checkIfUserEqualsIdGiven(id);
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty())
            return new ResponseEntity<>(USER_NOT_FOUND, HttpStatus.NOT_FOUND);

        clearUserCache();
        this.userRepository.deleteById(id);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> removeMovieFromFavouriteList(Long userId, Long movieId) {
        checkAuth.checkIfUserEqualsIdGiven(userId);
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        Movie movie = this.movieRepository.findById(movieId)
                .orElseThrow(() -> new NotFoundException(MOVIE_NOT_FOUND));

        if (!user.getMovieList().contains(movie))
            return new ResponseEntity<>("Movie is not on the favourite list", HttpStatus.NOT_FOUND);

        clearUserCache();
        user.removeMovie(movie);
        this.userRepository.save(user);
        return new ResponseEntity<>("Movie removed from the favourite list", HttpStatus.OK);
    }

    @Override
    @CacheEvict(key = "#id", value = "user")
    public UserDto update(Long id, UserUpdateDto userUpdateDto) {
        checkAuth.checkIfUserEqualsIdGiven(id);
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));

        Role role = user.getRoleId();

        if (userUpdateDto.getRoleId() != null)
            role = this.roleRepository.findById(userUpdateDto.getRoleId())
                    .orElseThrow(() -> new NotFoundException(ROLE_NOT_FOUND));
        if (userUpdateDto.getPassword() != null) {
            userUpdateDto.setPassword(encoder.encode(userUpdateDto.getPassword()));
        }
        userUpdateDto.setRoleId(null);
        User updatedUser = this.userConverter.converterUpdate(userUpdateDto, user);
        updatedUser.setRoleId(role);

        clearUserCache();
        return this.userConverter.converter(
                this.userRepository.save(updatedUser), UserDto.class);
    }

//    private void checkIfUserEqualsIdGiven(Long id) {
//        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
//        if(!this.REPOSITORY.findByEmail(email).get().getId().equals(id)){
//            throw new ConflictException("This id is not Yours");
//        }
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return UserAuthDto.builder().user(user).build();
    }

    private void clearUserCache() {
        Cache userCache = this.cacheManager.getCache("users");
        if(userCache!=null)userCache.clear();
    }
}
