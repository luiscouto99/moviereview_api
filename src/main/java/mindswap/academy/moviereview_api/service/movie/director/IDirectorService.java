package mindswap.academy.moviereview_api.service.movie.director;

import mindswap.academy.moviereview_api.exception.command.movie.director.DirectorDto;
import mindswap.academy.moviereview_api.exception.command.movie.director.DirectorUpdateDto;
import mindswap.academy.moviereview_api.service.IGenericService;

public interface IDirectorService extends IGenericService<DirectorDto,DirectorDto, DirectorUpdateDto> {
}
