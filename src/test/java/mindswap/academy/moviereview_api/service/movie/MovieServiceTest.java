package mindswap.academy.moviereview_api.service.movie;

import mindswap.academy.moviereview_api.command.movie.OutMovieDto;
import mindswap.academy.moviereview_api.converter.movie.IMovieConverter;
import mindswap.academy.moviereview_api.converter.movie.MovieConverter;
import mindswap.academy.moviereview_api.exception.BadRequestException;
import mindswap.academy.moviereview_api.exception.ConflictException;
import mindswap.academy.moviereview_api.persistence.repository.movie.IMovieRepository;
import mindswap.academy.moviereview_api.persistence.repository.movie.actor.IActorRepository;
import mindswap.academy.moviereview_api.persistence.repository.movie.director.IDirectorRepository;
import mindswap.academy.moviereview_api.persistence.repository.movie.genre.IGenreRepository;
import mindswap.academy.moviereview_api.persistence.repository.movie.writer.IWriterRepository;
import mindswap.academy.moviereview_api.persistence.repository.review.rating.IRatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static mindswap.academy.moviereview_api.persistence.model.movie.MoviePojo.*;
import static mindswap.academy.moviereview_api.persistence.model.movie.actor.ActorPojo.*;
import static mindswap.academy.moviereview_api.persistence.model.movie.director.DirectorPojo.*;
import static mindswap.academy.moviereview_api.persistence.model.movie.genre.GenrePojo.*;
import static mindswap.academy.moviereview_api.persistence.model.movie.writer.WriterPojo.*;
import static mindswap.academy.moviereview_api.persistence.model.review.rating.RatingPojo.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    IMovieService iMovieService;
    @Mock
    IMovieRepository iMovieRepository;
    @Mock
    IGenreRepository iGenreRepository;
    @Mock
    IActorRepository iActorRepository;
    @Mock
    IWriterRepository iWriterRepository;
    @Mock
    IDirectorRepository iDirectorRepository;
    @Mock
    IRatingRepository iRatingRepository;


    @BeforeEach
    public void setup() {
        this.iMovieService = new MovieService(new MovieConverter(new ModelMapper()),
                iMovieRepository,
                iGenreRepository,
                iActorRepository,
                iWriterRepository,
                iDirectorRepository,
                iRatingRepository,
                new SimpleCacheManager()
        );
    }
    @Nested
    class getMovie {
        @Test
        void test_addMovie() {
            when(iRatingRepository.findById(any()))
                    .thenReturn(Optional.ofNullable(RATING_EXAMPLE));
            when(iActorRepository.findById(any()))
                    .thenReturn(Optional.ofNullable(ACTOR_EXAMPLE));
            when(iGenreRepository.findById(any()))
                    .thenReturn(Optional.ofNullable(GENRE_EXAMPLE));
            when(iWriterRepository.findById(any()))
                    .thenReturn(Optional.ofNullable(WRITER_EXAMPLE));
            when(iDirectorRepository.findById(any()))
                    .thenReturn(Optional.ofNullable(DIRECTOR_EXAMPLE));
            when(iMovieRepository.save(any()))
                    .thenReturn(MOVIE_EXAMPLE);
            OutMovieDto result = iMovieService.add(MOVIE_DTO_EXAMPLE);
            assertEquals(OUT_MOVIE_DTO_EXAMPLE, result);
        }
        @Test
        void test_searchByMovieRating() {
            when(iMovieRepository.searchByMovieRating(any()))
                    .thenReturn(MOVIE_LIST_EXAMPLE);
            List<OutMovieDto> result = iMovieService.searchByMovieRating(any());
            assertEquals(OUT_MOVIE_DTO_LIST_EXAMPLE, result);
        }
        @Test
        void test_searchBy(){
            when(iMovieRepository.searchBy(1L,"title","year","contentRating"))
                    .thenReturn(MOVIE_LIST_EXAMPLE);
            List<OutMovieDto> result = iMovieService.searchBy(
                    1L,"title","year","contentRating");
            assertEquals(OUT_MOVIE_DTO_LIST_EXAMPLE, result);
        }
        @Test
       void test_searchByGenre(){
            when(iMovieRepository.searchByGenre("genre"))
                    .thenReturn(MOVIE_LIST_EXAMPLE);
            List<OutMovieDto> result = iMovieService.searchByGenre("genre");
            assertEquals(OUT_MOVIE_DTO_LIST_EXAMPLE, result);
        }
        @Test
        void test_searchActorMovieList(){
            when(iMovieRepository.searchHowManyMoviesActorHasByName("name"))
                    .thenReturn(MOVIE_LIST_EXAMPLE);
            List<OutMovieDto> result = iMovieService.searchActorMovieList("name");
            assertEquals(OUT_MOVIE_DTO_LIST_EXAMPLE, result);
        }
        @Test
        void test_update(){
            when(iActorRepository.findById(any()))
                    .thenReturn(Optional.ofNullable(ACTOR_EXAMPLE));
            when(iGenreRepository.findById(any()))
                    .thenReturn(Optional.ofNullable(GENRE_EXAMPLE));
            when(iWriterRepository.findById(any()))
                    .thenReturn(Optional.ofNullable(WRITER_EXAMPLE));
            when(iDirectorRepository.findById(any()))
                    .thenReturn(Optional.ofNullable(DIRECTOR_EXAMPLE));
            when(iMovieRepository.findById(any()))
                    .thenReturn(Optional.ofNullable(MOVIE_EXAMPLE));
            when(iMovieRepository.save(any()))
                    .thenReturn(MOVIE_EXAMPLE);
            OutMovieDto result = iMovieService.update(1L,UPDATE_MOVIE_DTO_EXAMPLE);
            assertEquals(OUT_MOVIE_DTO_EXAMPLE, result);
        }
        @Test
        void test_delete(){
            when(iMovieRepository.findById(any()))
                    .thenReturn(Optional.ofNullable(MOVIE_EXAMPLE));
            ResponseEntity<Object> result = iMovieService.delete(1L);
            assertEquals(RESPONSE_OK, result);
        }
        @Test
        void test_exception_add(){
            when(iMovieRepository.count(any()))
                    .thenReturn(1L);
            Executable action = () -> iMovieService.add(MOVIE_DTO_EXAMPLE);
            assertThrows(ConflictException.class, action);
        }
        @Test
        void test_getAll(){
            when(iMovieRepository.findAll())
                    .thenReturn(MOVIE_LIST_EXAMPLE);
            List<OutMovieDto> result = iMovieService.getAll();
            assertEquals(OUT_MOVIE_DTO_LIST_EXAMPLE, result);
        }
        @Test
        void test_exception_searchBy(){
            Executable action = () -> iMovieService.searchBy(
                    null, null,null,null);
            assertThrows(BadRequestException.class, action);
        }
    }

}