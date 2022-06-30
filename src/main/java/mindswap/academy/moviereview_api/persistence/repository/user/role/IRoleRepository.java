package mindswap.academy.moviereview_api.persistence.repository.user.role;

import mindswap.academy.moviereview_api.persistence.model.user.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByroleName(String name);
}
