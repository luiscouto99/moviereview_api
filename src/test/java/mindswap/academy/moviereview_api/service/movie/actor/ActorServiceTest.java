package mindswap.academy.moviereview_api.service.movie.actor;

import mindswap.academy.moviereview_api.command.movie.actor.ActorDto;
import mindswap.academy.moviereview_api.converter.movie.actor.ActorConverter;
import mindswap.academy.moviereview_api.exception.ConflictException;
import mindswap.academy.moviereview_api.persistence.repository.movie.actor.IActorRepository;

import org.hibernate.loader.entity.CacheEntityLoaderHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static mindswap.academy.moviereview_api.persistence.model.movie.MoviePojo.*;
import static mindswap.academy.moviereview_api.persistence.model.movie.actor.ActorPojo.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ActorServiceTest {
    @Mock
    IActorRepository actorRepository;
    IActorService actorService;
    @BeforeEach
    public void setup() {
        this.actorService = new ActorService(actorRepository,new ActorConverter(new ModelMapper()),new SimpleCacheManager());
    }

    @Nested
    class getActor {
        @Test
        void test_getAll(){
            when(actorRepository.findAll())
                    .thenReturn(ACTOR_LIST_EXAMPLE);
            List<ActorDto> result = actorService.getAll();
            assertEquals(ACTOR_DTO_LIST_EXAMPLE, result);
        }
        @Test
        void test_add(){
            when(actorRepository.save(any()))
                    .thenReturn(ACTOR_EXAMPLE);
            ActorDto result = actorService.add(ACTOR_DTO_EXAMPLE);

            assertEquals(ACTOR_DTO_EXAMPLE, result);
        }
        @Test
        void test_delete(){
            when(actorRepository.checkIfActorIsBeingUsed(any()))
                    .thenReturn(Optional.empty());
            when(actorRepository.findById(any()))
                    .thenReturn(Optional.ofNullable(ACTOR_EXAMPLE));
            ResponseEntity<Object> result = actorService.delete(1L);
            assertEquals(RESPONSE_OK, result);
        }
        @Test
        void test_update(){
            when(actorRepository.findById(any()))
                    .thenReturn(Optional.ofNullable(ACTOR_EXAMPLE));
            when(actorRepository.save(any()))
                    .thenReturn(ACTOR_EXAMPLE);
            ActorDto result = actorService.update(1L,ACTOR_UPDATE_DTO_EXAMPLE);
            assertEquals(ACTOR_DTO_EXAMPLE, result);
        }
        @Test
        void test_exception_delete(){
            when(actorRepository.checkIfActorIsBeingUsed(any()))
                    .thenReturn(Optional.ofNullable(ACTOR_EXAMPLE));
            Executable action = () -> actorService.delete(1L);
            assertThrows(ConflictException.class, action);
        }
    }
}
