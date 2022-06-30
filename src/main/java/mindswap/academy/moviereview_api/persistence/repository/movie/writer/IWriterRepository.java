package mindswap.academy.moviereview_api.persistence.repository.movie.writer;

import mindswap.academy.moviereview_api.persistence.model.movie.writer.Writer;
import org.springframework.data.jpa.repository.JpaRepository;



public interface IWriterRepository extends JpaRepository<Writer,Long> {
}
