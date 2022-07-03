package mindswap.academy.moviereview_api.controller.movie.writer;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.command.movie.writer.WriterDto;
import mindswap.academy.moviereview_api.command.movie.writer.WriterUpdateDto;
import mindswap.academy.moviereview_api.service.movie.writer.IWriterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/writer")
public class WriterController {
    private final IWriterService writerService;

    @GetMapping
    public List<WriterDto> getAll() {
        return this.writerService.getAll();
    }

    @PostMapping
    public WriterDto add(@Valid @RequestBody WriterDto writerDto) {
        return this.writerService.add(writerDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id")Long id) {
       return this.writerService.delete(id);
    }
    @PutMapping("/{id}")
    public WriterDto update(@PathVariable Long id,@Valid @RequestBody WriterUpdateDto writerUpdateDto) {
        return this.writerService.update(id,writerUpdateDto);
    }
}
