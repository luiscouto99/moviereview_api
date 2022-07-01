package mindswap.academy.moviereview_api.dataloader;

import lombok.RequiredArgsConstructor;
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

        List<Rating> ratingList = new ArrayList<>(Arrays.asList(
                Rating.builder().rate("★✰✰✰✰").build(),
                Rating.builder().rate("★★✰✰✰").build(),
                Rating.builder().rate("★★★✰✰").build(),
                Rating.builder().rate("★★★★✰").build(),
                Rating.builder().rate("★★★★★").build()
        ));

        this.iRatingRepository.saveAll(ratingList);

        List<Role> roles = Arrays.asList(
                Role.builder().roleName("User").build(),
                Role.builder().roleName("Admin").build());
        this.ROLE_REPOSITORY.saveAll(roles);


//        MovieList movieId = restTemplate.getForObject("https://imdb-api.com/en/API/MostPopularMovies/k_f19x9ubq", MovieList.class);
//        for (int i = 0; i < 5; i++) {
//            MovieApiDto movieDto = restTemplate.getForObject("https://imdb-api.com/en/API/Title/k_f19x9ubq/" + movieId.getItems().get(i).getId(), MovieApiDto.class);
//            Movie movie = this.movieConverter.converter(movieDto, Movie.class);
//            List<Director> newDirectorList = new ArrayList<>();
//            for (int i1 = 0; i1 < movie.getDirectorList().size(); i1++) {
//                try {
//                    this.directorRepository.saveAndFlush(movie.getDirectorList().get(i1));
//                } catch (Exception ignore) {
//                }
//                newDirectorList.add(this.directorRepository.findByName(movie.getDirectorList().get(i1).getName()).get());
//            }
//            List<Writer> newWriterList = new ArrayList<>();
//            for (int i1 = 0; i1 < movie.getWriterList().size(); i1++) {
//                try {
//                    this.writerRepository.saveAndFlush(movie.getWriterList().get(i1));
//                } catch (Exception ignore) {
//                }
//                newWriterList.add(this.writerRepository.findByName(movie.getWriterList().get(i1).getName()).get());
//            }
//            List<Actor> newActorList = new ArrayList<>();
//            for (int i1 = 0; i1 < movie.getActorList().size(); i1++) {
//                try {
//                    this.actorRepository.saveAndFlush(movie.getActorList().get(i1));
//                } catch (Exception ignore) {
//                }
//                newActorList.add(this.actorRepository.findByName(movie.getActorList().get(i1).getName()).get());
//            }
//            List<Genre> newGenreList = new ArrayList<>();
//            for (int i1 = 0; i1 < movie.getGenreList().size(); i1++) {
//                try {
//                    this.genreRepository.saveAndFlush(movie.getGenreList().get(i1));
//                } catch (Exception ignore) {
//                }
//                newGenreList.add(this.genreRepository.findByValue(movie.getGenreList().get(i1).getValue()).get());
//            }
//            movie.setGenreList(newGenreList);
//            movie.setActorList(newActorList);
//            movie.setWriterList(newWriterList);
//            movie.setDirectorList(newDirectorList);
//            this.movieRepository.save(movie);
//        }
    }
}
