package mindswap.academy.moviereview_api.config;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.exception.ConflictException;
import mindswap.academy.moviereview_api.persistence.repository.user.IUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class CheckAuth {
    private final IUserRepository userRepository;

    public void checkIfUserEqualsIdGiven(Long id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if(!this.userRepository.findByEmail(email).get().getId().equals(id)){
            throw new ConflictException("This id is not Yours");
        }
    }
}
