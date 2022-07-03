package mindswap.academy.moviereview_api.service.movie.genre;

import mindswap.academy.moviereview_api.command.movie.genre.GenreDto;
import mindswap.academy.moviereview_api.converter.movie.genre.GenreConverter;
import mindswap.academy.moviereview_api.exception.ConflictException;
import mindswap.academy.moviereview_api.persistence.repository.movie.genre.IGenreRepository;
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

import static mindswap.academy.moviereview_api.persistence.model.movie.MoviePojo.RESPONSE_OK;
import static mindswap.academy.moviereview_api.persistence.model.movie.actor.ActorPojo.*;
import static mindswap.academy.moviereview_api.persistence.model.movie.genre.GenrePojo.*;
import static mindswap.academy.moviereview_api.persistence.model.movie.genre.GenrePojo.GENRE_LIST_EXAMPLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GenreServiceTest {
    @Mock
    IGenreRepository genreRepository;
    IGenreService genreService;

    @BeforeEach
    public void setup() {
        this.genreService = new GenreService(new GenreConverter(new ModelMapper()),genreRepository,new SimpleCacheManager());
    }

    @Nested
    class getActor {
        @Test
        void test_getAll(){
            when(genreRepository.findAll())
                    .thenReturn(GENRE_LIST_EXAMPLE);
            List<GenreDto> result = genreService.getAll();
            assertEquals(GENRE_DTO_LIST_EXAMPLE, result);
        }
        @Test
        void test_add(){
            when(genreRepository.save(any()))
                    .thenReturn(GENRE_EXAMPLE);
            GenreDto result = genreService.add(GENRE_DTO_EXAMPLE);

            assertEquals(GENRE_DTO_EXAMPLE, result);
        }
        @Test
        void test_delete(){
            when(genreRepository.checkIfGenreIsBeingUsed(any()))
                    .thenReturn(Optional.empty());
            when(genreRepository.findById(any()))
                    .thenReturn(Optional.ofNullable(GENRE_EXAMPLE));
            ResponseEntity<Object> result = genreService.delete(1L);
            assertEquals(RESPONSE_OK, result);
        }
        @Test
        void test_update(){
            when(genreRepository.findById(any()))
                    .thenReturn(Optional.ofNullable(GENRE_EXAMPLE));
            when(genreRepository.save(any()))
                    .thenReturn(GENRE_EXAMPLE);
            GenreDto result = genreService.update(1L,GENRE_UPDATE_DTO_EXAMPLE);
            assertEquals(GENRE_DTO_EXAMPLE, result);
        }
        @Test
        void test_exception_delete(){
            when(genreRepository.checkIfGenreIsBeingUsed(any()))
                    .thenReturn(Optional.ofNullable(GENRE_EXAMPLE));
            Executable action = () -> genreService.delete(1L);
            assertThrows(ConflictException.class, action);
        }
    }

}
