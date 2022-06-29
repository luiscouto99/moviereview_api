package mindswap.academy.moviereview_api.service.review;

import mindswap.academy.moviereview_api.command.user.UserDto;
import mindswap.academy.moviereview_api.converter.review.IReviewConverter;
import mindswap.academy.moviereview_api.persistence.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.SecondaryTable;

@Service
public class ReviewService {

    private IReviewConverter iReviewConverter;

    @Autowired
    public ReviewService(IReviewConverter iReviewConverter) {
        this.iReviewConverter = iReviewConverter;
    }

    public void example(){
        User user = new User();
        UserDto userDto = this.iReviewConverter.converter(user, UserDto.class);
    }
}
