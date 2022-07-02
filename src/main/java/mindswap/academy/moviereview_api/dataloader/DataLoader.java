package mindswap.academy.moviereview_api.dataloader;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.persistence.model.movie.actor.Actor;
import mindswap.academy.moviereview_api.converter.movie.IMovieConverter;
import mindswap.academy.moviereview_api.dataloader.movieloader.MovieList;
import mindswap.academy.moviereview_api.command.movie.MovieApiDto;
import mindswap.academy.moviereview_api.persistence.model.movie.Movie;
import mindswap.academy.moviereview_api.persistence.model.movie.actor.Actor;
import mindswap.academy.moviereview_api.persistence.model.movie.director.Director;
import mindswap.academy.moviereview_api.persistence.model.movie.genre.Genre;
import mindswap.academy.moviereview_api.persistence.model.movie.writer.Writer;
import mindswap.academy.moviereview_api.persistence.model.user.role.Role;
import mindswap.academy.moviereview_api.persistence.repository.movie.IMovieRepository;
import mindswap.academy.moviereview_api.persistence.repository.movie.actor.IActorRepository;
import mindswap.academy.moviereview_api.persistence.repository.movie.director.IDirectorRepository;
import mindswap.academy.moviereview_api.persistence.repository.movie.genre.IGenreRepository;
import mindswap.academy.moviereview_api.persistence.repository.movie.writer.IWriterRepository;
import mindswap.academy.moviereview_api.persistence.repository.user.role.IRoleRepository;
import mindswap.academy.moviereview_api.persistence.model.review.rating.Rating;
import mindswap.academy.moviereview_api.persistence.repository.review.rating.IRatingRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import java.util.ArrayList;

@RequiredArgsConstructor
@Component
public class DataLoader implements ApplicationRunner {
    private final IRoleRepository ROLE_REPOSITORY;
    private final IGenreRepository genreRepository;
    private final IActorRepository actorRepository;
    private final IWriterRepository writerRepository;
    private final IDirectorRepository directorRepository;
    private final IMovieRepository movieRepository;
    private final IMovieConverter movieConverter;
    private final RestTemplate restTemplate;
    private final IRatingRepository iRatingRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // review
        List<Rating> ratingList = new ArrayList<>(Arrays.asList(
                Rating.builder().rate("★✰✰✰✰").build(),
                Rating.builder().rate("★★✰✰✰").build(),
                Rating.builder().rate("★★★✰✰").build(),
                Rating.builder().rate("★★★★✰").build(),
                Rating.builder().rate("★★★★★").build()
        ));
        this.iRatingRepository.saveAll(ratingList);

        // user
        List<Role> roleList = new ArrayList<>(Arrays.asList(
                Role.builder().roleName("User").build(),
                Role.builder().roleName("Admin").build()
        ));
        this.ROLE_REPOSITORY.saveAll(roleList);
        List<Director> newDirectorList = new ArrayList<>(Arrays.asList(
                Director.builder().name("OLA").build(),
                Director.builder().name("adieus").build(),
                Director.builder().name("xiu").build()
        ));
        List<Writer> newWriterList = new ArrayList<>(Arrays.asList(
                Writer.builder().name("OLA").build(),
                Writer.builder().name("adieus").build(),
                Writer.builder().name("xiu").build()
        ));
        List<Actor> newActorList = new ArrayList<>(Arrays.asList(
                Actor.builder().image("nope").name("oink").build(),
                Actor.builder().image("nope").name("ole").build(),
                Actor.builder().image("nope").name("bue").build()
        ));
        List<Genre> newGenreList = new ArrayList<>(Arrays.asList(
                Genre.builder().value("Drama").build(),
                Genre.builder().value("Action").build(),
                Genre.builder().value("Fantasy").build()
        ));
        this.genreRepository.saveAll(newGenreList);
        this.actorRepository.saveAll(newActorList);
        this.writerRepository.saveAll(newWriterList);
        this.directorRepository.saveAll(newDirectorList);


        List<Movie> newMovieList = new ArrayList<>(Arrays.asList(
                Movie.builder().genreList(newGenreList)
                        .title("aaa")
                        .year("2222")
                        .actorList(newActorList)
                        .directorList(newDirectorList)
                        .writerList(newWriterList).build()
        ));
        this.movieRepository.saveAll(newMovieList);

//        MovieList movieListId = restTemplate.getForObject("https://imdb-api.com/en/API/MostPopularMovies/k_f19x9ubq", MovieList.class);
//        for (int i = 0; i < 1; i++) {
//            MovieApiDto movieDto = restTemplate.getForObject("https://imdb-api.com/en/API/Title/k_f19x9ubq/" + movieListId.getItems().get(i).getId(), MovieApiDto.class);
//            Movie movie = this.movieConverter.converter(movieDto, Movie.class);
//            List<Director> newDirectorList = new ArrayList<>();
//            List<Writer> newWriterList = new ArrayList<>();
//            List<Actor> newActorList = new ArrayList<>();
//            List<Genre> newGenreList = new ArrayList<>();
//            addDirectors(movie, newDirectorList);
//            addWriters(movie, newWriterList);
//            addActors(movie, newActorList);
//            addGenre(movie, newGenreList);
//            movie.setGenreList(newGenreList);
//            movie.setActorList(newActorList);
//            movie.setWriterList(newWriterList);
//            movie.setDirectorList(newDirectorList);
//            this.movieRepository.save(movie);
//        }
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
