package mindswap.academy.moviereview_api.dataloader;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.command.movie.MovieApiDto;
import mindswap.academy.moviereview_api.dataloader.movieloader.MovieList;
import mindswap.academy.moviereview_api.persistence.model.movie.actor.Actor;
import mindswap.academy.moviereview_api.converter.movie.IMovieConverter;
import mindswap.academy.moviereview_api.persistence.model.movie.Movie;
import mindswap.academy.moviereview_api.persistence.model.movie.director.Director;
import mindswap.academy.moviereview_api.persistence.model.movie.genre.Genre;
import mindswap.academy.moviereview_api.persistence.model.movie.writer.Writer;
import mindswap.academy.moviereview_api.persistence.model.user.User;
import mindswap.academy.moviereview_api.persistence.model.user.role.Role;
import mindswap.academy.moviereview_api.persistence.repository.movie.IMovieRepository;
import mindswap.academy.moviereview_api.persistence.repository.movie.actor.IActorRepository;
import mindswap.academy.moviereview_api.persistence.repository.movie.director.IDirectorRepository;
import mindswap.academy.moviereview_api.persistence.repository.movie.genre.IGenreRepository;
import mindswap.academy.moviereview_api.persistence.repository.movie.writer.IWriterRepository;
import mindswap.academy.moviereview_api.persistence.repository.user.IUserRepository;
import mindswap.academy.moviereview_api.persistence.repository.user.role.IRoleRepository;
import mindswap.academy.moviereview_api.persistence.model.review.rating.Rating;
import mindswap.academy.moviereview_api.persistence.repository.review.rating.IRatingRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import java.util.ArrayList;

@RequiredArgsConstructor
@Component
public class DataLoader implements ApplicationRunner {
    private final IRoleRepository roleRepository;
    private final IUserRepository userRepository;
    private final IGenreRepository genreRepository;
    private final IActorRepository actorRepository;
    private final IWriterRepository writerRepository;
    private final IDirectorRepository directorRepository;
    private final IMovieRepository movieRepository;
    private final IMovieConverter movieConverter;
    private final RestTemplate restTemplate;
    private final IRatingRepository iRatingRepository;
    private final PasswordEncoder encoder;
    private int offSet = 0;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // review
        List<Rating> ratingList = new ArrayList<>(Arrays.asList(
                Rating.builder().rate("★✰✰✰✰").build(),
                Rating.builder().rate("★★✰✰✰").build(),
                Rating.builder().rate("★★★✰✰").build(),
                Rating.builder().rate("★★★★✰").build(),
                Rating.builder().rate("★★★★★").build()));
        // user
        List<Role> roleList = new ArrayList<>(Arrays.asList(
                        Role.builder().roleName("USER").build(),
                        Role.builder().roleName("ADMIN").build()));
        List<User> userList = new ArrayList<>(List.of(
                User.builder().roleId(roleList.get(1)).firstName("João").lastName("Silva").email("joao@email.com")
                        .dateOfBirth(LocalDate.parse("1998-08-03")).dateOfAccountCreation(LocalDate.now())
                        .password(encoder.encode("palavrapass")).build(),
                User.builder().roleId(roleList.get(1)).firstName("Luis").lastName("Couto").email("luis@email.com")
                        .dateOfBirth(LocalDate.parse("1980-09-07")).dateOfAccountCreation(LocalDate.now())
                        .password(encoder.encode("palavrapass")).build(),
                User.builder().roleId(roleList.get(1)).firstName("Nuno").lastName("Carmo").email("nuno@email.com")
                        .dateOfBirth(LocalDate.parse("1970-09-07")).dateOfAccountCreation(LocalDate.now())
                        .password(encoder.encode("palavrapass")).build()));

        addRating(ratingList);
        addRoles(roleList);
        addUsers(userList);

        try {
            MovieList movieListId = restTemplate.getForObject("https://imdb-api.com/en/API/Top250Movies/" + getKey(), MovieList.class);
            for (int i = 0; i < 200; i++) {
                MovieApiDto movieDto = restTemplate.getForObject("https://imdb-api.com/en/API/Title/" + getKey() + "/" + movieListId.getItems().get(i).getId(), MovieApiDto.class);
                Movie movie = this.movieConverter.converter(movieDto, Movie.class);
                List<Director> newDirectorList = new ArrayList<>();
                List<Writer> newWriterList = new ArrayList<>();
                List<Actor> newActorList = new ArrayList<>();
                List<Genre> newGenreList = new ArrayList<>();
                addDirectors(movie, newDirectorList);
                addWriters(movie, newWriterList);
                addActors(movie, newActorList);
                addGenre(movie, newGenreList);
                movie.setGenreList(newGenreList);
                movie.setActorList(newActorList);
                movie.setWriterList(newWriterList);
                movie.setDirectorList(newDirectorList);
                addMovie(movie);
            }
        } catch (Exception ignored) {
        }
    }

    private void addRating(List<Rating> ratingList) {
        ratingList.forEach(rating -> {
            if (!this.iRatingRepository.exists(Example.of(rating))) {
                this.iRatingRepository.save(rating);
            }
        });
    }

    private void addRoles(List<Role> roleList) {
        roleList.forEach(role -> {
            if (!this.roleRepository.exists(Example.of(role))) {
                this.roleRepository.save(role);
            }
        });
    }

    private void addUsers(List<User> userList) {
        userList.forEach(user -> {
            if (this.userRepository.findByEmail(user.getEmail()).isEmpty()) {
                this.userRepository.save(user);
            }
        });
    }

    private String getKey() {
        List<String> keys = new ArrayList<>(List.of("k_f19x9ubq", "k_xzd0qgbx", "k_9zo30c7a", "k_vc3jh1hq", "k_eo1o2c83"));
        int index = offSet++ % keys.size();
        return keys.get(index);
    }

    private void addMovie(Movie movie) {
        if (this.movieRepository.findAll(Example.of(movie)).isEmpty()) {
            this.movieRepository.save(movie);
        }
    }

    private void addGenre(Movie movie, List<Genre> newGenreList) {
        for (int i1 = 0; i1 < movie.getGenreList().size(); i1++) {
            if (this.genreRepository.findByValue(movie.getGenreList().get(i1).getValue()).isEmpty()) {
                this.genreRepository.saveAndFlush(movie.getGenreList().get(i1));
            }
            newGenreList.add(this.genreRepository.findByValue(movie.getGenreList().get(i1).getValue()).get());
        }
    }

    private void addActors(Movie movie, List<Actor> newActorList) {
        for (int i1 = 0; i1 < movie.getActorList().size(); i1++) {
            if (this.actorRepository.findByName(movie.getActorList().get(i1).getName()).isEmpty()) {
                this.actorRepository.saveAndFlush(movie.getActorList().get(i1));
            }
            newActorList.add(this.actorRepository.findByName(movie.getActorList().get(i1).getName()).get());
        }
    }

    private void addWriters(Movie movie, List<Writer> newWriterList) {
        for (int i1 = 0; i1 < movie.getWriterList().size(); i1++) {
            if (this.writerRepository.findByName(movie.getWriterList().get(i1).getName()).isEmpty()) {
                this.writerRepository.saveAndFlush(movie.getWriterList().get(i1));
            }
            newWriterList.add(this.writerRepository.findByName(movie.getWriterList().get(i1).getName()).get());
        }
    }

    private void addDirectors(Movie movie, List<Director> newDirectorList) {
        for (int i1 = 0; i1 < movie.getDirectorList().size(); i1++) {
            if (this.directorRepository.findByName(movie.getDirectorList().get(i1).getName()).isEmpty()) {
                this.directorRepository.saveAndFlush(movie.getDirectorList().get(i1));
            }
            newDirectorList.add(this.directorRepository.findByName(movie.getDirectorList().get(i1).getName()).get());
        }
    }
}
