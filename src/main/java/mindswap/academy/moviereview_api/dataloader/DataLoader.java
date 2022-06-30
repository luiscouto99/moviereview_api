package mindswap.academy.moviereview_api.dataloader;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpResponse;
@RequiredArgsConstructor
@Component
public class DataLoader implements ApplicationRunner {
   private final RestTemplate restTemplate;
    @Override
    public void run(ApplicationArguments args) throws Exception {
//        MovieSummary movieSummary = restTemplate.getForObject("https://imdb-api.com/en/API/Title/k_f19x9ubq/tt0111161",MovieSummary.class);
//        assert movieSummary != null;
//        System.out.println(movieSummary);
//        System.out.println(movieSummary.fullTitle);
//        System.out.println(movieSummary.title);
//        System.out.println(movieSummary.year);
//        System.out.println(movieSummary.releaseDate);
//        System.out.println(movieSummary.runtimeStr);
//
//
//        for (int i = 0; i < movieSummary.actorList.size(); i++) {
//            System.out.println(movieSummary.actorList.get(i).name);
//            System.out.println(movieSummary.actorList.get(i).image);
//            System.out.println(movieSummary.actorList.get(i).asCharacter);
//        }
//
//
//        for (int i = 0; i < movieSummary.directorList.size(); i++) {
//            System.out.println(movieSummary.directorList.get(i).name);
//        }
//        for (int i = 0; i < movieSummary.writerList.size(); i++) {
//            System.out.println(movieSummary.writerList.get(i).name);
//        }
//        for (int i = 0; i < movieSummary.genreList.size(); i++) {
//            System.out.println(movieSummary.genreList.get(i).value);
//        }
//        System.out.println(movieSummary.contentRating);
    }
}
