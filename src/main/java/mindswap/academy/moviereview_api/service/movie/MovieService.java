package mindswap.academy.moviereview_api.service.movie;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.command.movie.MovieDto;
import mindswap.academy.moviereview_api.command.movie.MovieUpdateDto;
import mindswap.academy.moviereview_api.command.movie.OutMovieDto;
import mindswap.academy.moviereview_api.converter.movie.IMovieConverter;
import mindswap.academy.moviereview_api.exception.BadRequestException;
import mindswap.academy.moviereview_api.exception.ConflictException;
import mindswap.academy.moviereview_api.exception.NotFoundException;
import mindswap.academy.moviereview_api.persistence.model.movie.Movie;
import mindswap.academy.moviereview_api.persistence.model.movie.actor.Actor;
import mindswap.academy.moviereview_api.persistence.model.movie.director.Director;
import mindswap.academy.moviereview_api.persistence.model.movie.genre.Genre;
import mindswap.academy.moviereview_api.persistence.model.movie.writer.Writer;
import mindswap.academy.moviereview_api.persistence.repository.movie.IMovieRepository;
import mindswap.academy.moviereview_api.persistence.repository.movie.actor.IActorRepository;
import mindswap.academy.moviereview_api.persistence.repository.movie.director.IDirectorRepository;
import mindswap.academy.moviereview_api.persistence.repository.movie.genre.IGenreRepository;
import mindswap.academy.moviereview_api.persistence.repository.movie.writer.IWriterRepository;
import mindswap.academy.moviereview_api.persistence.repository.review.rating.IRatingRepository;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static mindswap.academy.moviereview_api.exception.ExceptionMessages.*;

@Service
@RequiredArgsConstructor
public class MovieService implements IMovieService {
    private final IMovieConverter movieConverter;
    private final IMovieRepository movieRepository;
    private final IGenreRepository genreRepository;
    private final IActorRepository actorRepository;
    private final IWriterRepository writerRepository;
    private final IDirectorRepository directorRepository;
    private final IRatingRepository ratingRepository;

    @Override
    public List<OutMovieDto> getAll() {
        List<Movie> movieList = this.movieRepository.findAll();
        if (movieList.isEmpty()) throw new NotFoundException(MOVIE_NOT_FOUND);
        return this.movieConverter.converterList(movieList, OutMovieDto.class);
    }

    @Override
    public OutMovieDto add(MovieDto movieDto) {
        Movie movie = this.movieConverter.converter(movieDto, Movie.class);

        if (this.movieRepository.count(Example.of(movie)) != 0) {
            throw new ConflictException(MOVIE_ALREADY_EXISTS);
        }

        checkIfActorExists(movie);
        checkIfWriterExists(movie);
        checkIfDirectorExists(movie);
        checkIfGenreExists(movie);
        movie.setRatingId(this.ratingRepository.findById(5L).get());

        Movie savedMovie = this.movieRepository.save(movie);
        return this.movieConverter.converter(savedMovie, OutMovieDto.class);
    }

    private void checkIfGenreExists(Movie movie) {
        for (int i = 0; i < movie.getGenreList().size(); i++) {
            this.genreRepository.findById(movie.getGenreList().get(i).getId())
                    .orElseThrow(() -> new NotFoundException(GENRE_NOT_FOUND));
        }
    }

    private void checkIfDirectorExists(Movie movie) {
        for (int i = 0; i < movie.getDirectorList().size(); i++) {
            this.directorRepository.findById(movie.getDirectorList().get(i).getId())
                    .orElseThrow(() -> new NotFoundException(DIRECTOR_NOT_FOUND));
        }
    }

    private void checkIfWriterExists(Movie movie) {
        for (int i = 0; i < movie.getWriterList().size(); i++) {
            this.writerRepository.findById(movie.getWriterList().get(i).getId())
                    .orElseThrow(() -> new NotFoundException(WRITER_NOT_FOUND));
        }
    }

    private void checkIfActorExists(Movie movie) {
        for (int i = 0; i < movie.getActorList().size(); i++) {
            this.actorRepository.findById(movie.getActorList().get(i).getId())
                    .orElseThrow(() -> new NotFoundException(ACTOR_NOT_FOUND));
        }
    }

    @Override
    public ResponseEntity<Object> delete(Long id) {
        Movie movie = this.movieRepository.findById(id).orElseThrow(() -> new NotFoundException(MOVIE_NOT_FOUND));
        this.movieRepository.delete(movie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public OutMovieDto update(Long id, MovieUpdateDto movieUpdateDto) {
        Movie oldMovie = this.movieRepository.findById(id).orElseThrow(() -> new NotFoundException(MOVIE_NOT_FOUND));
        if (movieUpdateDto.getActorList() != null) updateActorList(movieUpdateDto, oldMovie);
        if (movieUpdateDto.getDirectorList() != null) updateDirectorList(movieUpdateDto, oldMovie);
        if (movieUpdateDto.getWriterList() != null) updateWriterList(movieUpdateDto, oldMovie);
        if (movieUpdateDto.getGenreList() != null) updateGenreList(movieUpdateDto, oldMovie);
        Movie updatedMovie = this.movieConverter.converterUpdate(movieUpdateDto, oldMovie);
        this.movieRepository.save(updatedMovie);
        return this.movieConverter.converter(updatedMovie, OutMovieDto.class);
    }

    private void updateActorList(MovieUpdateDto movieUpdateDto, Movie updatedMovie) {
        Set<Actor> checkList = new HashSet<>();
        List<Actor> updatedActorList = new ArrayList<>();
        for (int i = 0; i < movieUpdateDto.getActorList().size(); i++) {
            Actor actor = this.actorRepository.findById(movieUpdateDto.getActorList().get(i).getId())
                    .orElseThrow(() -> new NotFoundException(ACTOR_NOT_FOUND));
            if (checkList.add(actor)) {
                updatedActorList.add(actor);
            }
        }
        movieUpdateDto.setActorList(null);
        updatedMovie.setActorList(updatedActorList);
    }

    private void updateDirectorList(MovieUpdateDto movieUpdateDto, Movie updatedMovie) {
        Set<Director> checkList = new HashSet<>();
        List<Director> updatedDirectorList = new ArrayList<>();
        for (int i = 0; i < movieUpdateDto.getDirectorList().size(); i++) {
            Director director = this.directorRepository.findById(movieUpdateDto.getDirectorList().get(i).getId())
                    .orElseThrow(() -> new NotFoundException(DIRECTOR_NOT_FOUND));
            if (checkList.add(director)) {
                updatedDirectorList.add(director);
            }
        }
        movieUpdateDto.setDirectorList(null);
        updatedMovie.setDirectorList(updatedDirectorList);
    }

    private void updateWriterList(MovieUpdateDto movieUpdateDto, Movie updatedMovie) {
        Set<Writer> checkList = new HashSet<>();
        List<Writer> updatedWriterList = new ArrayList<>();
        for (int i = 0; i < movieUpdateDto.getWriterList().size(); i++) {
            Writer writer = this.writerRepository.findById(movieUpdateDto.getWriterList().get(i).getId())
                    .orElseThrow(() -> new NotFoundException(WRITER_NOT_FOUND));
            if (checkList.add(writer)) {
                updatedWriterList.add(writer);
            }
        }
        movieUpdateDto.setWriterList(null);
        updatedMovie.setWriterList(updatedWriterList);
    }

    private void updateGenreList(MovieUpdateDto movieUpdateDto, Movie updatedMovie) {
        Set<Genre> checkList = new HashSet<>();
        List<Genre> updatedGenreList = new ArrayList<>();
        for (int i = 0; i < movieUpdateDto.getGenreList().size(); i++) {
            Genre genre = this.genreRepository.findById(movieUpdateDto.getGenreList().get(i).getId())
                    .orElseThrow(() -> new NotFoundException(GENRE_NOT_FOUND));
            if (checkList.add(genre)) {
                updatedGenreList.add(genre);
            }
        }
        movieUpdateDto.setGenreList(null);
        updatedMovie.setGenreList(updatedGenreList);
    }

    @Override
    public List<OutMovieDto> searchBy(Long id, String title, String year, String contentRanting) {
        if (title != null && title.isEmpty()) title = null;
        if (year != null && year.isEmpty()) year = null;
        if (contentRanting != null && contentRanting.isEmpty()) contentRanting = null;
        if (id == null && title == null && year == null && contentRanting == null) {
            throw new BadRequestException(AT_LEAST_1_PARAMETER);
        }
        List<Movie> movieList = movieRepository.searchBy(id, title, year, contentRanting);
        if (movieList.isEmpty()) throw new NotFoundException(MOVIE_NOT_FOUND);
        return this.movieConverter.converterList(movieList, OutMovieDto.class);
    }

    @Override
    public List<OutMovieDto> searchActorMovieList(String name) {
        if (name.isEmpty()) throw new BadRequestException(EMPTY_NAME);
        List<Movie> movieList = this.movieRepository.searchHowManyMoviesActorHasByName(name);
        if (movieList.isEmpty()) throw new NotFoundException(MOVIE_NOT_FOUND);
        return this.movieConverter.converterList(movieList, OutMovieDto.class);
    }

    @Override
    public List<OutMovieDto> searchByGenre(String genre) {
        List<Movie> movieList = this.movieRepository.searchByGenre(genre);
        if (movieList.isEmpty()) throw new NotFoundException(MOVIE_NOT_FOUND);
        return this.movieConverter.converterList(movieList, OutMovieDto.class);
    }

    @Override
    public List<OutMovieDto> searchByMovieRating(Long id) {
        List<Movie> movieList = this.movieRepository.searchByMovieRating(id);
        if(movieList.isEmpty()) throw new NotFoundException(MOVIE_NOT_FOUND);
        return this.movieConverter.converterList(movieList,OutMovieDto.class);
    }
}
