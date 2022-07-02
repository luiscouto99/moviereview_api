package mindswap.academy.moviereview_api.persistence.model.movie;

import mindswap.academy.moviereview_api.converter.movie.IMovieConverter;
import mindswap.academy.moviereview_api.converter.movie.MovieConverter;
import mindswap.academy.moviereview_api.persistence.repository.movie.IMovieRepository;
import mindswap.academy.moviereview_api.persistence.repository.movie.actor.IActorRepository;
import mindswap.academy.moviereview_api.persistence.repository.movie.director.IDirectorRepository;
import mindswap.academy.moviereview_api.persistence.repository.movie.genre.IGenreRepository;
import mindswap.academy.moviereview_api.persistence.repository.movie.writer.IWriterRepository;
import mindswap.academy.moviereview_api.persistence.repository.review.rating.IRatingRepository;
import mindswap.academy.moviereview_api.service.movie.IMovieService;
import mindswap.academy.moviereview_api.service.movie.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MovieTest {

    IMovieService iMovieService;
    //IMovieConverter movieConverter;
    @Mock
    IMovieRepository movieRepository;
    IGenreRepository genreRepository;
    IActorRepository actorRepository;
    IWriterRepository writerRepository;
    IDirectorRepository directorRepository;
    IRatingRepository ratingRepository;

    @BeforeEach
    public void setup() {
        this.iMovieService = new MovieService(
                new MovieConverter(new ModelMapper()),
                movieRepository,
                genreRepository,
                actorRepository,
                writerRepository,
                directorRepository,
                ratingRepository
        );
    }

    @Nested
    class getMovie() {

        @Test
        void testGetMovieById() {

        }
    }


}