package mindswap.academy.moviereview_api.dataloader;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.persistence.model.user.role.Role;
import mindswap.academy.moviereview_api.persistence.repository.user.role.IRoleRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
public class DataLoader implements ApplicationRunner {
    private final IRoleRepository ROLE_REPOSITORY;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Role> roles = Arrays.asList(
                Role.builder().roleName("User").build(),
                Role.builder().roleName("Admin").build());
        this.ROLE_REPOSITORY.saveAll(roles);

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
