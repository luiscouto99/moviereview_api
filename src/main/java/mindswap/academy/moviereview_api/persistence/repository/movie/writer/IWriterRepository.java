package mindswap.academy.moviereview_api.persistence.repository.movie.writer;

import mindswap.academy.moviereview_api.persistence.model.movie.writer.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;
import java.util.Optional;


public interface IWriterRepository extends JpaRepository<Writer,Long> {
    Optional<Writer> findByName(String name);
}
