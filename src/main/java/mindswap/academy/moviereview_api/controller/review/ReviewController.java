package mindswap.academy.moviereview_api.controller.review;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.command.review.ReviewDeleteDto;
import mindswap.academy.moviereview_api.command.review.ReviewDto;
import mindswap.academy.moviereview_api.command.review.ReviewUpdateDto;
import mindswap.academy.moviereview_api.persistence.model.movie.Movie;
import mindswap.academy.moviereview_api.persistence.model.review.rating.Rating;
import mindswap.academy.moviereview_api.persistence.model.user.User;
import mindswap.academy.moviereview_api.service.review.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public List<ReviewDto> getAll() {
        return this.reviewService.getAll();
    }

    @GetMapping("/byuser/{id}")
    public List<ReviewDto> getReviewsFromUser(@PathVariable("id") Long id) {
        return this.reviewService.getReviewsFromUser(id);
    }

    @GetMapping("/search")
    public List<ReviewDto> searchBy(@RequestParam(value = "ratingId", required = false) Long ratingId,
                                  @RequestParam(value = "movieId", required = false) Long movieId,
                                  @RequestParam(value = "userId", required = false) Long userId) {
        return this.reviewService.searchBy(ratingId, movieId, userId);
    }

    @PostMapping
    public ReviewDto add(@Valid @RequestBody ReviewDto reviewDto) {
        return this.reviewService.add(reviewDto);
    }

    @DeleteMapping()
    public ResponseEntity<Object> delete(@Valid @RequestBody ReviewDeleteDto reviewDeleteDto) {
        return this.reviewService.delete(reviewDeleteDto);
    }

    @PutMapping("/{id}")
    public ReviewDto update(@PathVariable("id") Long id, @Valid @RequestBody ReviewUpdateDto reviewUpdateDto) {
        return this.reviewService.update(id, reviewUpdateDto);
    }
}
