package mindswap.academy.moviereview_api.controller.review.rating;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.command.review.ReviewDto;
import mindswap.academy.moviereview_api.command.review.ReviewUpdateDto;
import mindswap.academy.moviereview_api.command.review.rating.RatingDto;
import mindswap.academy.moviereview_api.command.review.rating.RatingUpdateDto;
import mindswap.academy.moviereview_api.service.review.rating.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @GetMapping
    public List<RatingDto> getAll(){
        return this.ratingService.getAll();
    }

    @PostMapping
    public RatingDto add(@Valid @RequestBody RatingDto ratingDto) {
        return this.ratingService.add(ratingDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        return this.ratingService.delete(id);
    }

    @PutMapping("/{id}")
    public RatingDto update(@PathVariable("id") Long id, @Valid @RequestBody RatingUpdateDto ratingUpdateDto) {
        return this.ratingService.update(id, ratingUpdateDto);
    }
}
