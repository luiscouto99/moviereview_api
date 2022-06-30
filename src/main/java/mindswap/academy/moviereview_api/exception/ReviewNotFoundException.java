package mindswap.academy.moviereview_api.exception;

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(String message){
       super(message);
    }
}
