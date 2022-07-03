package mindswap.academy.moviereview_api.service.review;

import mindswap.academy.moviereview_api.command.movie.OutMovieDto;
import mindswap.academy.moviereview_api.command.review.ReviewDto;
import mindswap.academy.moviereview_api.command.review.ReviewUpdateDto;
import mindswap.academy.moviereview_api.config.CheckAuth;
import mindswap.academy.moviereview_api.converter.review.ReviewConverter;
import mindswap.academy.moviereview_api.exception.BadRequestException;
import mindswap.academy.moviereview_api.exception.ConflictException;
import mindswap.academy.moviereview_api.exception.NotFoundException;
import mindswap.academy.moviereview_api.persistence.model.movie.Movie;
import mindswap.academy.moviereview_api.persistence.repository.movie.IMovieRepository;
import mindswap.academy.moviereview_api.persistence.repository.review.IReviewRepository;
import mindswap.academy.moviereview_api.persistence.repository.review.rating.IRatingRepository;
import mindswap.academy.moviereview_api.persistence.repository.user.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import static mindswap.academy.moviereview_api.persistence.model.movie.MoviePojo.*;
import static mindswap.academy.moviereview_api.persistence.model.review.ReviewPojo.*;
import static mindswap.academy.moviereview_api.persistence.model.review.rating.RatingPojo.*;
import static mindswap.academy.moviereview_api.persistence.model.user.UserPojo.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    IReviewService iReviewService;
    @Mock
    IReviewRepository iReviewRepository;
    @Mock
    IRatingRepository iRatingRepository;
    @Mock
    IUserRepository iUserRepository;
    @Mock
    IMovieRepository iMovieRepository;
    @Mock
    CheckAuth checkAuth;

    @BeforeEach
    public void setup() {
        this.iReviewService = new ReviewService(
                iReviewRepository,
                iRatingRepository,
                iUserRepository,
                iMovieRepository,
                new ReviewConverter(new ModelMapper()),
                new SimpleCacheManager(),
                checkAuth
        );
    }

    @Nested
    class getReview {

        @Test
        void test_getAll() {
            when(iReviewRepository.findAll())
                    .thenReturn(REVIEW_LIST_EXAMPLE);

            List<ReviewDto> result = iReviewService.getAll();

            assertEquals(REVIEW_DTO_LIST_EXAMPLE, result);
        }

        @Test
        void test_getReviewsFromUser() {
            when(iUserRepository.findById(any()))
                    .thenReturn(Optional.ofNullable(USER_EXAMPLE));

            List<ReviewDto> result = iReviewService.getReviewsFromUser(any());

            assertEquals(REVIEW_DTO_LIST_EXAMPLE, result);
        }

        @Test
        void test_searchBy(){
            when(iReviewRepository.searchBy(1L,1L,1L))
                    .thenReturn(REVIEW_LIST_EXAMPLE);

            List<ReviewDto> result = iReviewService.searchBy(
                    1L,1L,1L);

            assertEquals(REVIEW_DTO_LIST_EXAMPLE, result);
        }

        @Test
        void test_addReview() {
            when(iUserRepository.findById(any()))
                    .thenReturn(Optional.ofNullable(USER_EXAMPLE));

            when(iMovieRepository.findById(any()))
                    .thenReturn(Optional.ofNullable(MOVIE_EXAMPLE));

            when(iRatingRepository.findById(any()))
                    .thenReturn(Optional.ofNullable(RATING_EXAMPLE));

            when(iReviewRepository.save(any()))
                    .thenReturn(REVIEW_EXAMPLE);

            ReviewDto result = iReviewService.add(REVIEW_DTO_EXAMPLE);

            assertEquals(REVIEW_DTO_EXAMPLE, result);
        }

        @Test
        void test_delete(){
            when(iReviewRepository.searchByUserIdAndReviewId(any(), any()))
                    .thenReturn(Optional.ofNullable(REVIEW_EXAMPLE));

            ResponseEntity<Object> result = iReviewService.delete(REVIEW_DELETE_DTO_EXAMPLE);

            assertEquals(RESPONSE_DELETED, result);
        }

        @Test
        void test_update() {
            when(iUserRepository.findById(any()))
                    .thenReturn(Optional.ofNullable(USER_EXAMPLE));

            when(iMovieRepository.findById(any()))
                    .thenReturn(Optional.ofNullable(MOVIE_EXAMPLE));

            when(iReviewRepository.searchByUserIdAndReviewId(any(), any()))
                    .thenReturn(Optional.ofNullable(REVIEW_EXAMPLE));

            when(iRatingRepository.findById(any()))
                    .thenReturn(Optional.ofNullable(RATING_EXAMPLE));

            ReviewDto result = iReviewService.update(any(), REVIEW_UPDATE_DTO_EXAMPLE);

            assertEquals(REVIEW_DTO_EXAMPLE, result);
        }

        @Test
        void test_exception_searchBy(){
            Executable action = () -> iReviewService.searchBy(
                    null, null,null);
            assertThrows(BadRequestException.class, action);
        }
    }
}
