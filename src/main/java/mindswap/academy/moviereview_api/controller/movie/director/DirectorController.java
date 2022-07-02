package mindswap.academy.moviereview_api.controller.movie.director;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.command.movie.director.DirectorDto;
import mindswap.academy.moviereview_api.command.movie.director.DirectorUpdateDto;
import mindswap.academy.moviereview_api.service.movie.director.IDirectorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/director")
public class DirectorController {
    private final IDirectorService directorService;

    @GetMapping
    public List<DirectorDto> getAll() {
        return this.directorService.getAll();
    }

    @PostMapping
    public DirectorDto add(@Valid @RequestBody DirectorDto directorDto) {
        return this.directorService.add(directorDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return this.directorService.delete(id);
    }

    @PutMapping("/{id}")
    public DirectorDto update(@PathVariable Long id,@Valid @RequestBody DirectorUpdateDto directorUpdateDto) {
        return this.directorService.update(id,directorUpdateDto);
    }
}
