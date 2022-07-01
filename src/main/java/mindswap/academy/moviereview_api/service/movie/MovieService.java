package mindswap.academy.moviereview_api.service.movie;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.command.movie.MovieDto;
import mindswap.academy.moviereview_api.command.movie.MovieUpdateDto;
import mindswap.academy.moviereview_api.command.movie.OutMovieDto;
import mindswap.academy.moviereview_api.converter.movie.IMovieConverter;
import mindswap.academy.moviereview_api.persistence.model.movie.Movie;
import mindswap.academy.moviereview_api.persistence.repository.movie.IMovieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class MovieService implements IMovieService{
    private final IMovieRepository movieRepository;
    private final IMovieConverter movieConverter;
    @Override
    public List<OutMovieDto> getAll() {
        List<Movie> movieList = this.movieRepository.findAll();
        return this.movieConverter.converterList(movieList,OutMovieDto.class);
    }

    @Override
    public OutMovieDto add(MovieDto movieDto) {
        Movie movie = this.movieConverter.converter(movieDto, Movie.class);
        Movie savedMovie = this.movieRepository.save(movie);
        return this.movieConverter.converter(savedMovie, OutMovieDto.class);
    }

    @Override
    public ResponseEntity<Object> delete(Long id) {
        return null;
    }

    @Override
    public OutMovieDto update(Long id, MovieUpdateDto movieUpdateDto) {
        return null;
    }
}
