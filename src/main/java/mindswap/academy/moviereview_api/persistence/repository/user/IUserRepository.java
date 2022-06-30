package mindswap.academy.moviereview_api.persistence.repository.user;

import mindswap.academy.moviereview_api.persistence.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
}
