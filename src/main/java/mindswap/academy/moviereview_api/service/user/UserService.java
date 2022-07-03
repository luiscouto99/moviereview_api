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
import java.util.Objects;
import java.util.Optional;

import static mindswap.academy.moviereview_api.exception.ExceptionMessages.*;

@Service
@AllArgsConstructor
public class UserService implements IUserService, UserDetailsService {
    private final IUserRepository USER_REPOSITORY;
    private final IUserConverter CONVERTER;
    private final IRoleRepository ROLE_REPOSITORY;
    private final IMovieRepository MOVIE_REPOSITORY;
    private final CacheManager CACHE_MANAGER;
    private final PasswordEncoder ENCONDER;
    private final CheckAuth CHECK_AUTH;

    @Override
    @Cacheable("users")
    public List<UserDto> getAll() {
        return this.CONVERTER.converterList(
                this.USER_REPOSITORY.findAll(), UserDto.class);
    }

    @Override
    @Cacheable(key = "#id", value = "user")
    public UserDto getUser(Long id) {
        CHECK_AUTH.checkIfUserEqualsIdGiven(id);
        User user = this.USER_REPOSITORY.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return this.CONVERTER.converter(user, UserDto.class);
    }

    @Override
    @Cacheable("users")
    public List<UserDto> search(Long roleId, String firstName, String lastName, String email) {
        if (roleId == null && firstName.equals("") && lastName.equals("") && email.equals(""))
            throw new BadRequestException(AT_LEAST_1_PARAMETER);

        return this.CONVERTER.converterList(
                this.USER_REPOSITORY.search(roleId, firstName, lastName, email), UserDto.class);
    }

    @Override
    @Cacheable("users")
    public List<MovieDto> getFavouriteList(Long userId) {
        CHECK_AUTH.checkIfUserEqualsIdGiven(userId);
        User user = this.USER_REPOSITORY.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return this.CONVERTER.converterList(user.getMovieList(), MovieDto.class);
    }

    @Override
    public UserDto add(UserDto userDto) {
        this.ROLE_REPOSITORY.findById(userDto.getRoleId())
                .orElseThrow(() -> new NotFoundException(ROLE_NOT_FOUND));
        this.USER_REPOSITORY.findByEmail(userDto.getEmail())
                .ifPresent(user -> {
                    throw new ConflictException(EMAIL_REGISTERED);
                });

        clearUserCache();
        User user = this.CONVERTER.converter(userDto, User.class);
        user.setPassword(ENCONDER.encode(user.getPassword()));
        this.USER_REPOSITORY.save(user);
        return this.CONVERTER.converter(user, UserDto.class);
    }

    @Override
    public ResponseEntity<Object> addMovie(Long userId, Long movieId) {
        CHECK_AUTH.checkIfUserEqualsIdGiven(userId);
        User user = this.USER_REPOSITORY.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        Movie movie = this.MOVIE_REPOSITORY.findById(movieId)
                .orElseThrow(() -> new NotFoundException(MOVIE_NOT_FOUND));

        if (user.getMovieList().contains(movie))
            return new ResponseEntity<>("Movie is already on the favourite list", HttpStatus.CONFLICT);

        clearUserCache();
        user.addMovie(movie);
        this.USER_REPOSITORY.save(user);
        return new ResponseEntity<>("Movie added to the favourite list", HttpStatus.OK);
    }

    @Override
    @CacheEvict(key = "#id", value = "user")
    public ResponseEntity<Object> delete(Long id) {
        CHECK_AUTH.checkIfUserEqualsIdGiven(id);
        Optional<User> user = this.USER_REPOSITORY.findById(id);
        if (user.isEmpty())
            return new ResponseEntity<>(USER_NOT_FOUND, HttpStatus.NOT_FOUND);

        clearUserCache();
        this.USER_REPOSITORY.deleteById(id);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> removeMovieFromFavouriteList(Long userId, Long movieId) {
        CHECK_AUTH.checkIfUserEqualsIdGiven(userId);
        User user = this.USER_REPOSITORY.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        Movie movie = this.MOVIE_REPOSITORY.findById(movieId)
                .orElseThrow(() -> new NotFoundException(MOVIE_NOT_FOUND));

        if (!user.getMovieList().contains(movie))
            return new ResponseEntity<>("Movie is not on the favourite list", HttpStatus.NOT_FOUND);

        clearUserCache();
        user.removeMovie(movie);
        this.USER_REPOSITORY.save(user);
        return new ResponseEntity<>("Movie removed from the favourite list", HttpStatus.OK);
    }

    @Override
    @CacheEvict(key = "#id", value = "user")
    public UserDto update(Long id, UserUpdateDto userUpdateDto) {
        CHECK_AUTH.checkIfUserEqualsIdGiven(id);
        User user = this.USER_REPOSITORY.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));

        Role role = user.getRoleId();

        if (userUpdateDto.getRoleId() != null)
            role = this.ROLE_REPOSITORY.findById(userUpdateDto.getRoleId())
                    .orElseThrow(() -> new NotFoundException(ROLE_NOT_FOUND));
        if (userUpdateDto.getPassword() != null) {
            userUpdateDto.setPassword(ENCONDER.encode(userUpdateDto.getPassword()));
        }
        userUpdateDto.setRoleId(null);
        User updatedUser = this.CONVERTER.converterUpdate(userUpdateDto, user);
        updatedUser.setRoleId(role);

        clearUserCache();
        return this.CONVERTER.converter(
                this.USER_REPOSITORY.save(updatedUser), UserDto.class);
    }

//    private void checkIfUserEqualsIdGiven(Long id) {
//        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
//        if(!this.REPOSITORY.findByEmail(email).get().getId().equals(id)){
//            throw new ConflictException("This id is not Yours");
//        }
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.USER_REPOSITORY.findByEmail(email).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return UserAuthDto.builder().user(user).build();
    }

    private void clearUserCache() {
        Cache userCache = this.CACHE_MANAGER.getCache("users");
        if(userCache!=null)userCache.clear();
    }
}
