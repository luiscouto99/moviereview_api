package mindswap.academy.moviereview_api.service.review;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.exception.command.review.ReviewDto;
import mindswap.academy.moviereview_api.exception.command.review.ReviewUpdateDto;
import mindswap.academy.moviereview_api.converter.review.IReviewConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService implements IReviewService{

    private final IReviewConverter iReviewConverter;


    /*public void example(){
        User user = new User();
        UserDto userDto = this.iReviewConverter.converter(user, UserDto.class);
    }*/


    @Override
    public List<ReviewDto> getAll() {
        return null;
    }

    @Override
    public ReviewDto add(ReviewDto reviewDto) {
        return null;
    }

    @Override
    public ResponseEntity<Object> delete(Long id) {
        return null;
    }

    @Override
    public ReviewDto update(Long id, ReviewUpdateDto reviewUpdateDto) {
        return null;
    }
}
