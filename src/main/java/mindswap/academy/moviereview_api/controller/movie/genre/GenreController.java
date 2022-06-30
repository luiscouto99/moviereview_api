package mindswap.academy.moviereview_api.controller.movie.genre;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.exception.command.movie.genre.GenreDto;
import mindswap.academy.moviereview_api.exception.command.movie.genre.GenreUpdateDto;
import mindswap.academy.moviereview_api.service.movie.genre.IGenreService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public void add(@RequestBody GenreDto genreDto) {
        this.genreService.add(genreDto);
    }

    @PutMapping("{id}")
    public GenreDto update(@PathVariable Long id, @RequestBody GenreUpdateDto genreUpdateDto) {
        return this.genreService.update(id,genreUpdateDto);
    }
}
