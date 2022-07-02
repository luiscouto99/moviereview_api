package mindswap.academy.moviereview_api.service.review;

import mindswap.academy.moviereview_api.command.review.ReviewDto;
import mindswap.academy.moviereview_api.converter.review.ReviewConverter;
import mindswap.academy.moviereview_api.persistence.repository.movie.IMovieRepository;
import mindswap.academy.moviereview_api.persistence.repository.review.IReviewRepository;
import mindswap.academy.moviereview_api.persistence.repository.review.rating.IRatingRepository;
import mindswap.academy.moviereview_api.persistence.repository.user.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static mindswap.academy.moviereview_api.mockedpojo.ReviewMockedPojo.*;
import static mindswap.academy.moviereview_api.mockedpojo.UserMockedPojo.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @Mock
    IReviewRepository iReviewRepository;

    IReviewService iReviewService;
    IRatingRepository iRatingRepository;

    @Mock
    IUserRepository iUserRepository;
    IMovieRepository iMovieRepository;

    @BeforeEach
    public void setup() {
        this.iReviewService = new ReviewService(
                iReviewRepository,
                iRatingRepository,
                iUserRepository,
                iMovieRepository,
                new ReviewConverter(new ModelMapper()));
    }

    @Nested
    class getReviewFromUser {

        @Test
        void testGetReviewsFromUser() {
            when(iUserRepository.findById(1L))
                    .thenReturn(Optional.of(USER_EXAMPLE));

            List<ReviewDto> result = iReviewService.getReviewsFromUser(1L);

            assertEquals(List.of(REVIEW_DTO_EXAMPLE), result);
        }
    }
}
