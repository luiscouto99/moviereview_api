package mindswap.academy.moviereview_api.controller.movie.writer;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.exception.command.movie.writer.WriterDto;
import mindswap.academy.moviereview_api.exception.command.movie.writer.WriterUpdateDto;
import mindswap.academy.moviereview_api.service.movie.writer.IWriterService;
import org.springframework.web.bind.annotation.*;

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
    public void add(@RequestBody WriterDto writerDto) {
        this.writerService.add(writerDto);
    }

    @PutMapping("{id}")
    public WriterDto update(@PathVariable Long id, @RequestBody WriterUpdateDto writerUpdateDto) {
        return this.writerService.update(id,writerUpdateDto);
    }
}
