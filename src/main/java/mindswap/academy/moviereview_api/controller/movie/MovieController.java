package mindswap.academy.moviereview_api.controller.movie;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.exception.command.movie.MovieDto;
import mindswap.academy.moviereview_api.exception.command.movie.OutMovieDto;
import mindswap.academy.moviereview_api.service.movie.IMovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {
    private final IMovieService movieService;
    @GetMapping
    public List<OutMovieDto> getAll() {
        return this.movieService.getAll();
    }
    @PostMapping
    public void add(@RequestBody MovieDto movieDto) {
        this.movieService.add(movieDto);
    }
}
