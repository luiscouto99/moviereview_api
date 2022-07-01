package mindswap.academy.moviereview_api.persistence.model.movie;

import mindswap.academy.moviereview_api.converter.movie.IMovieConverter;
import mindswap.academy.moviereview_api.converter.movie.MovieConverter;
import mindswap.academy.moviereview_api.converter.user.UserConverter;
import mindswap.academy.moviereview_api.persistence.repository.movie.IMovieRepository;
import mindswap.academy.moviereview_api.service.movie.IMovieService;
import mindswap.academy.moviereview_api.service.movie.MovieService;
import mindswap.academy.moviereview_api.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MovieTest {

    IMovieService movieService;
    IMovieRepository movieRepository;

    @BeforeEach
    public void setup() {
        this.movieService = new MovieService(movieRepository, new MovieConverter(new ModelMapper()));
    }

}