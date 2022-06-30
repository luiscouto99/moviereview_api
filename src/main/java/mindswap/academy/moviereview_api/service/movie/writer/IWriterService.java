package mindswap.academy.moviereview_api.service.movie.writer;

import mindswap.academy.moviereview_api.exception.command.movie.writer.WriterDto;
import mindswap.academy.moviereview_api.exception.command.movie.writer.WriterUpdateDto;
import mindswap.academy.moviereview_api.service.IGenericService;

public interface IWriterService extends IGenericService<WriterDto,WriterDto, WriterUpdateDto> {
}
