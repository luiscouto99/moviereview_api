package mindswap.academy.moviereview_api.converter.movie;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class MovieConverter implements IMovieConverter{
    private final ModelMapper modelMapper;
    public MovieConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setSkipNullEnabled(true);
    }
    @Override
    public ModelMapper getMapper() {
        return modelMapper;
    }
}
