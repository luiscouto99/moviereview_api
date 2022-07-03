package mindswap.academy.moviereview_api.service.movie.writer;

import mindswap.academy.moviereview_api.command.movie.actor.ActorDto;
import mindswap.academy.moviereview_api.command.movie.writer.WriterDto;
import mindswap.academy.moviereview_api.converter.movie.writer.WriterConverter;
import mindswap.academy.moviereview_api.exception.ConflictException;
import mindswap.academy.moviereview_api.persistence.repository.movie.writer.IWriterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static mindswap.academy.moviereview_api.persistence.model.movie.MoviePojo.RESPONSE_OK;
import static mindswap.academy.moviereview_api.persistence.model.movie.actor.ActorPojo.*;
import static mindswap.academy.moviereview_api.persistence.model.movie.writer.WriterPojo.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WriterServiceTest {
    @Mock
    IWriterRepository writerRepository;
    IWriterService writerService;

    @BeforeEach
    public void setup() {
        this.writerService = new WriterService(new WriterConverter(new ModelMapper()), writerRepository);
    }

    @Nested
    class getActor {
        @Test
        void test_getAll(){
            when(writerRepository.findAll())
                    .thenReturn(WRITER_LIST_EXAMPLE);
            List<WriterDto> result = writerService.getAll();
            assertEquals(WRITER_DTO_LIST_EXAMPLE, result);
        }
        @Test
        void test_add(){
            when(writerRepository.save(any()))
                    .thenReturn(WRITER_EXAMPLE);
            WriterDto result = writerService.add(WRITER_DTO_EXAMPLE);

            assertEquals(WRITER_DTO_EXAMPLE, result);
        }
        @Test
        void test_delete(){
            when(writerRepository.checkIfWriterIsBeingUsed(any()))
                    .thenReturn(Optional.empty());
            when(writerRepository.findById(any()))
                    .thenReturn(Optional.ofNullable(WRITER_EXAMPLE));
            ResponseEntity<Object> result = writerService.delete(1L);
            assertEquals(RESPONSE_OK, result);
        }
        @Test
        void test_update(){
            when(writerRepository.findById(any()))
                    .thenReturn(Optional.ofNullable(WRITER_EXAMPLE));
            when(writerRepository.save(any()))
                    .thenReturn(WRITER_EXAMPLE);
            WriterDto result = writerService.update(1L,WRITER_UPDATE_DTO_EXAMPLE);
            assertEquals(WRITER_DTO_EXAMPLE, result);
        }
        @Test
        void test_exception_delete(){
            when(writerRepository.checkIfWriterIsBeingUsed(any()))
                    .thenReturn(Optional.ofNullable(WRITER_EXAMPLE));
            Executable action = () -> writerService.delete(1L);
            assertThrows(ConflictException.class, action);

        }
    }

}
