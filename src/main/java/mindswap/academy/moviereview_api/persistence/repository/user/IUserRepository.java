package mindswap.academy.moviereview_api.persistence.repository.user;

import mindswap.academy.moviereview_api.persistence.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE (users.role_id_fk = :roleId OR :roleId ISNULL) AND " +
            "(UPPER(users.first_name) LIKE UPPER(CONCAT('%', :firstName, '%')) OR :firstName ISNULL) AND " +
            "(UPPER(users.last_name) LIKE UPPER(CONCAT('%', :lastName, '%')) OR :lastName ISNULL) AND" +
            "(UPPER(users.email) LIKE UPPER(CONCAT('%', :email, '%')) OR :email ISNULL)", nativeQuery = true)
    List<User> search(Long roleId, String firstName, String lastName, String email);
}
