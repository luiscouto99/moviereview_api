package mindswap.academy.moviereview_api.service.movie.genre;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.command.movie.genre.GenreDto;
import mindswap.academy.moviereview_api.command.movie.genre.GenreUpdateDto;
import mindswap.academy.moviereview_api.converter.movie.genre.IGenreConverter;
import mindswap.academy.moviereview_api.exception.ConflictException;
import mindswap.academy.moviereview_api.exception.NotFoundException;
import mindswap.academy.moviereview_api.persistence.model.movie.genre.Genre;
import mindswap.academy.moviereview_api.persistence.model.movie.writer.Writer;
import mindswap.academy.moviereview_api.persistence.repository.movie.genre.IGenreRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static mindswap.academy.moviereview_api.exception.ExceptionMessages.*;

@RequiredArgsConstructor
@Service
public class GenreService implements IGenreService {
    private final IGenreConverter genreConverter;
    private final IGenreRepository genreRepository;

    @Override
    public List<GenreDto> getAll() {
        List<Genre> genreList = this.genreRepository.findAll();
        return this.genreConverter.converterList(genreList, GenreDto.class);
    }

    @Override
    public GenreDto add(GenreDto genreDto) {
        Genre genre = this.genreConverter.converter(genreDto, Genre.class);
        Genre savedGenre= this.genreRepository.save(genre);
        return this.genreConverter.converter(savedGenre, GenreDto.class);
    }

    @Override
    public ResponseEntity<Object> delete(Long id) {
        this.genreRepository.checkIfGenreIsBeingUsed(id)
                .ifPresent((writer) -> {
                    throw new ConflictException(GENRE_IS_BEING_USED);
                });
        Genre genre = this.genreRepository.findById(id).orElseThrow(() -> new NotFoundException(GENRE_NOT_FOUND));
        this.genreRepository.delete(genre);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public GenreDto update(Long id, GenreUpdateDto genreUpdateDto) {
        Genre oldGenre = this.genreRepository.findById(id).orElseThrow(() -> new NotFoundException("Genre not found"));
        Genre updatedGenre = this.genreRepository.save(this.genreConverter.converterUpdate(genreUpdateDto, oldGenre));
        return this.genreConverter.converter(updatedGenre, GenreDto.class);
    }
}
