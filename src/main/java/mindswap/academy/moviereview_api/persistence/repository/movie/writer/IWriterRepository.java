package mindswap.academy.moviereview_api.persistence.repository.movie.writer;

import mindswap.academy.moviereview_api.persistence.model.movie.writer.Writer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface IWriterRepository extends JpaRepository<Writer,Long> {
    @Query(value = "SELECT writer.*\n" +
            "FROM movie\n" +
            "INNER JOIN writer_movie_list\n" +
            "ON writer_movie_list.movie_id = movie.id \n" +
            "INNER JOIN writer\n" +
            "ON writer_movie_list.writer_id = writer.id \n" +
            "WHERE writer.id = :id ;", nativeQuery = true)
    Optional<Writer> checkIfWriterIsBeingUsed(Long id);

    Optional<Writer> findByName(String name);
}
