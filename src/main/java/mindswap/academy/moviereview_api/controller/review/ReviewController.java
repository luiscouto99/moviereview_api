package mindswap.academy.moviereview_api.controller.review;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.command.review.ReviewDto;
import mindswap.academy.moviereview_api.service.review.IReviewService;
import mindswap.academy.moviereview_api.service.review.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ReviewDto add(ReviewDto reviewDto) {
        return this.reviewService.add(reviewDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        return this.reviewService.delete(id);
    }
}
