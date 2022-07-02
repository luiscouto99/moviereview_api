package mindswap.academy.moviereview_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class MoviereviewApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviereviewApiApplication.class, args);
	}
}
