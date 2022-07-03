package mindswap.academy.moviereview_api.controller.movie;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.command.movie.MovieDto;
import mindswap.academy.moviereview_api.command.movie.MovieUpdateDto;
import mindswap.academy.moviereview_api.command.movie.OutMovieDto;
import mindswap.academy.moviereview_api.service.movie.IMovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movie")
public class MovieController {

    private final IMovieService movieService;

    @GetMapping
    public List<OutMovieDto> getAll() {
        return this.movieService.getAll();
    }

    @GetMapping("/search")
    public List<OutMovieDto> search(@RequestParam(value = "id", required = false) Long id,
                                    @RequestParam(value = "title", required = false) String title,
                                    @RequestParam(value = "year", required = false) String year,
                                    @RequestParam(value = "contentRanting", required = false) String contentRanting) {
        return this.movieService.searchBy(id, title, year,contentRanting);
    }
    @GetMapping("/search/genre")
    public List<OutMovieDto> searchByGenre(@RequestParam(value = "genre", required = false) String genre) {
        return this.movieService.searchByGenre(genre);
    }
    @GetMapping("/search/actor")
    public List<OutMovieDto> searchByActor(@RequestParam(value = "name", required = true) String name) {
        return this.movieService.searchActorMovieList(name);
    }
    @GetMapping("/search/rating")
    public List<OutMovieDto> search(@RequestParam(value = "id", required = false) Long id){
        return this.movieService.searchByMovieRating(id);
    }
    @PutMapping("/{id}")
    public OutMovieDto update(@PathVariable("id") Long id,@Valid @RequestBody MovieUpdateDto movieUpdateDto) {
        return this.movieService.update(id,movieUpdateDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        return this.movieService.delete(id);
    }

    @PostMapping
    public void add(@Valid @RequestBody MovieDto movieDto) {
        this.movieService.add(movieDto);
    }
}
