package mindswap.academy.moviereview_api.converter.movie.writer;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class WriterConverter implements IWriterConverter{
    private final ModelMapper modelMapper;
    public WriterConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setSkipNullEnabled(true);
    }
    @Override
    public ModelMapper getMapper() {
        return modelMapper;
    }
}
