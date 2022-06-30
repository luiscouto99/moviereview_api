package mindswap.academy.moviereview_api.controller.movie.director;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.exception.command.movie.director.DirectorDto;
import mindswap.academy.moviereview_api.exception.command.movie.director.DirectorUpdateDto;
import mindswap.academy.moviereview_api.service.movie.director.IDirectorService;
import org.springframework.web.bind.annotation.*;

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
    public void add(@RequestBody DirectorDto directorDto) {
        this.directorService.add(directorDto);
    }

    @PutMapping("{id}")
    public DirectorDto update(@PathVariable Long id, @RequestBody DirectorUpdateDto directorUpdateDto) {
        return this.directorService.update(id,directorUpdateDto);
    }
}
