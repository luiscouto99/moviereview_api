package mindswap.academy.moviereview_api.controller.movie.genre;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.command.movie.genre.GenreDto;
import mindswap.academy.moviereview_api.command.movie.genre.GenreUpdateDto;
import mindswap.academy.moviereview_api.service.movie.genre.IGenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/genre")
public class GenreController {
    private final IGenreService genreService;
    @GetMapping
    public List<GenreDto> getAll() {
        return this.genreService.getAll();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id")Long id) {
        return this.genreService.delete(id);
    }

    @PostMapping
    public GenreDto add(@Valid @RequestBody GenreDto genreDto) {
       return this.genreService.add(genreDto);
    }

    @PutMapping("/{id}")
    public GenreDto update(@PathVariable Long id,@Valid @RequestBody GenreUpdateDto genreUpdateDto) {
        return this.genreService.update(id,genreUpdateDto);
    }
}
