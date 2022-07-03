package mindswap.academy.moviereview_api.service.movie.director;

import mindswap.academy.moviereview_api.command.movie.actor.ActorDto;
import mindswap.academy.moviereview_api.command.movie.director.DirectorDto;
import mindswap.academy.moviereview_api.converter.movie.director.DirectorConverter;
import mindswap.academy.moviereview_api.exception.ConflictException;
import mindswap.academy.moviereview_api.persistence.repository.movie.director.IDirectorRepository;
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
import static mindswap.academy.moviereview_api.persistence.model.movie.director.DirectorPojo.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DirectorServiceTest {
    @Mock
    IDirectorRepository directorRepository;
    IDirectorService directorService;

    @BeforeEach
    public void setup() {
        this.directorService = new DirectorService(directorRepository,new DirectorConverter(new ModelMapper()),new SimpleCacheManager());
    }

    @Nested
    class getDirector {
        @Test
        void test_getAll(){
            when(directorRepository.findAll())
                    .thenReturn(DIRECTOR_LIST_EXAMPLE);
            List<DirectorDto> result = directorService.getAll();
            assertEquals(DIRECTOR_DTO_LIST_EXAMPLE, result);
        }
        @Test
        void test_add(){
            when(directorRepository.save(any()))
                    .thenReturn(DIRECTOR_EXAMPLE);
            DirectorDto result = directorService.add(DIRECTOR_DTO_EXAMPLE);

            assertEquals(DIRECTOR_DTO_EXAMPLE, result);
        }
        @Test
        void test_delete(){
            when(directorRepository.checkIfDirectorIsBeingUsed(any()))
                    .thenReturn(Optional.empty());
            when(directorRepository.findById(any()))
                    .thenReturn(Optional.ofNullable(DIRECTOR_EXAMPLE));
            ResponseEntity<Object> result = directorService.delete(1L);
            assertEquals(RESPONSE_OK, result);
        }
        @Test
        void test_update(){
            when(directorRepository.findById(any()))
                    .thenReturn(Optional.ofNullable(DIRECTOR_EXAMPLE));
            when(directorRepository.save(any()))
                    .thenReturn(DIRECTOR_EXAMPLE);
            DirectorDto result = directorService.update(1L,DIRECTOR_UPDATE_DTO_EXAMPLE);
            assertEquals(DIRECTOR_DTO_EXAMPLE, result);
        }
        @Test
        void test_exception_delete(){
            when(directorRepository.checkIfDirectorIsBeingUsed(any()))
                    .thenReturn(Optional.ofNullable(DIRECTOR_EXAMPLE));
            Executable action = () -> directorService.delete(1L);
            assertThrows(ConflictException.class, action);
        }
    }

}
