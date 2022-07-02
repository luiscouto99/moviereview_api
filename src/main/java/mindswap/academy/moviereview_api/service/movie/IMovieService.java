package mindswap.academy.moviereview_api.service.movie;

import mindswap.academy.moviereview_api.command.movie.MovieDto;
import mindswap.academy.moviereview_api.command.movie.MovieUpdateDto;
import mindswap.academy.moviereview_api.command.movie.OutMovieDto;
import mindswap.academy.moviereview_api.service.IGenericService;

import java.util.List;

public interface IMovieService extends IGenericService<OutMovieDto,MovieDto,MovieUpdateDto> {
    List<OutMovieDto> searchBy(Long id, String title, String year,String contentRanting);
    List<OutMovieDto> searchActorMovieList(String name);
    List<OutMovieDto> searchByGenre(String genre);
}
