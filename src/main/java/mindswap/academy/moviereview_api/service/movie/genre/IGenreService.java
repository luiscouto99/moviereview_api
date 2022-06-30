package mindswap.academy.moviereview_api.service.movie.genre;

import mindswap.academy.moviereview_api.command.movie.genre.GenreDto;
import mindswap.academy.moviereview_api.command.movie.genre.GenreUpdateDto;
import mindswap.academy.moviereview_api.service.IGenericService;

public interface IGenreService extends IGenericService<GenreDto,GenreDto, GenreUpdateDto> {
}
