package mindswap.academy.moviereview_api.service.movie;

import mindswap.academy.moviereview_api.command.movie.MovieDto;
import mindswap.academy.moviereview_api.command.movie.MovieUpdateDto;
import mindswap.academy.moviereview_api.command.movie.OutMovieDto;
import mindswap.academy.moviereview_api.service.IGenericService;

public interface IMovieService extends IGenericService<OutMovieDto,MovieDto,MovieUpdateDto> {
}
