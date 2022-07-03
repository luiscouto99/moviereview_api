package mindswap.academy.moviereview_api.service.movie.genre;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.command.movie.genre.GenreDto;
import mindswap.academy.moviereview_api.command.movie.genre.GenreUpdateDto;
import mindswap.academy.moviereview_api.converter.movie.genre.IGenreConverter;
import mindswap.academy.moviereview_api.exception.NotFoundException;
import mindswap.academy.moviereview_api.persistence.model.movie.genre.Genre;
import mindswap.academy.moviereview_api.persistence.repository.movie.genre.IGenreRepository;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class GenreService implements IGenreService {
    private final IGenreConverter genreConverter;
    private final IGenreRepository genreRepository;
    private final CacheManager cacheManager;

    @Override
    @Cacheable("genres")
    public List<GenreDto> getAll() {
        List<Genre> genreList = this.genreRepository.findAll();
        return this.genreConverter.converterList(genreList, GenreDto.class);
    }

    @Override
    public GenreDto add(GenreDto genreDto) {
        Genre genre = this.genreConverter.converter(genreDto, Genre.class);
        Objects.requireNonNull(this.cacheManager.getCache("genres")).clear();
        Objects.requireNonNull(this.cacheManager.getCache("movies")).clear();
        Genre savedGenre = this.genreRepository.save(genre);
        return this.genreConverter.converter(savedGenre, GenreDto.class);
    }

    @Override
    @CacheEvict(key = "#id", value = "genre")
    public ResponseEntity<Object> delete(Long id) {
        Objects.requireNonNull(this.cacheManager.getCache("genres")).clear();
        Objects.requireNonNull(this.cacheManager.getCache("movies")).clear();
        return null;
    }

    @Override
    @CacheEvict(key = "#id", value = "genre")
    public GenreDto update(Long id, GenreUpdateDto genreUpdateDto) {
        Genre oldGenre = this.genreRepository.findById(id).orElseThrow(() -> new NotFoundException("Genre not found"));
        Objects.requireNonNull(this.cacheManager.getCache("genres")).clear();
        Objects.requireNonNull(this.cacheManager.getCache("movies")).clear();
        Genre updatedGenre = this.genreRepository.save(this.genreConverter.converterUpdate(genreUpdateDto, oldGenre));
        return this.genreConverter.converter(updatedGenre, GenreDto.class);
    }
}
