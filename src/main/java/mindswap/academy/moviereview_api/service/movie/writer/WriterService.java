package mindswap.academy.moviereview_api.service.movie.writer;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.command.movie.writer.WriterDto;
import mindswap.academy.moviereview_api.command.movie.writer.WriterUpdateDto;
import mindswap.academy.moviereview_api.converter.movie.writer.IWriterConverter;
import mindswap.academy.moviereview_api.exception.NotFoundException;
import mindswap.academy.moviereview_api.persistence.model.movie.writer.Writer;
import mindswap.academy.moviereview_api.persistence.repository.movie.writer.IWriterRepository;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class WriterService implements IWriterService{
    private final IWriterConverter writerConverter;
    private final IWriterRepository writerRepository;
    private final CacheManager cacheManager;
    @Override
    @Cacheable("writers")
    public List<WriterDto> getAll() {
        List<Writer> writerList = this.writerRepository.findAll();
        return this.writerConverter.converterList(writerList, WriterDto.class);
    }

    @Override
    public WriterDto add(WriterDto writerDto) {
        Writer writer = this.writerConverter.converter(writerDto, Writer.class);
        Objects.requireNonNull(this.cacheManager.getCache("writers")).clear();
        Objects.requireNonNull(this.cacheManager.getCache("movies")).clear();
        Writer savedWriter= this.writerRepository.save(writer);
        return this.writerConverter.converter(savedWriter, WriterDto.class);
    }

    @Override
    @CacheEvict(key = "#id", value = "writer")
    public ResponseEntity<Object> delete(Long id) {
        Objects.requireNonNull(this.cacheManager.getCache("writers")).clear();
        Objects.requireNonNull(this.cacheManager.getCache("movies")).clear();
        return null;
    }

    @Override
    @CacheEvict(key = "#id", value = "writer")
    public WriterDto update(Long id, WriterUpdateDto writerUpdateDto) {
        Writer oldWriter = this.writerRepository.findById(id).orElseThrow(() -> new NotFoundException("Writer not found"));
        Objects.requireNonNull(this.cacheManager.getCache("writers")).clear();
        Objects.requireNonNull(this.cacheManager.getCache("movies")).clear();
        Writer updatedWriter = this.writerRepository.save(this.writerConverter.converterUpdate(writerUpdateDto, oldWriter));
        return this.writerConverter.converter(updatedWriter, WriterDto.class);
    }
}
