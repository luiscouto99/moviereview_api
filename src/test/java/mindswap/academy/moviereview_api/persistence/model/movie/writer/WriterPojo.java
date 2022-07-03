package mindswap.academy.moviereview_api.persistence.model.movie.writer;



import mindswap.academy.moviereview_api.command.movie.writer.WriterDto;
import mindswap.academy.moviereview_api.command.movie.writer.WriterUpdateDto;
import mindswap.academy.moviereview_api.persistence.model.movie.writer.Writer;

import java.util.ArrayList;
import java.util.List;

public class WriterPojo {
    public static final WriterUpdateDto WRITER_UPDATE_DTO_EXAMPLE = WriterUpdateDto.builder()
            .name("something")
            .build();
    // Models
    public static final Writer WRITER_EXAMPLE = Writer.builder()
            .id(1L)
            .name("something")
            .build();

    public static final WriterDto WRITER_DTO_EXAMPLE = WriterDto.builder()
            .id(1L)
            .name("something")
            .build();


    // Lists
    public static final List<Writer> WRITER_LIST_EXAMPLE = new ArrayList<>(List.of(
            Writer.builder()
                    .id(1L)
                    .name("something")
                    .build()
    ));

    public static final List<WriterDto> WRITER_DTO_LIST_EXAMPLE = new ArrayList<>(List.of(
            WriterDto.builder()
                    .id(1L)
                    .name("something")
                    .build()
    ));
}
