package mindswap.academy.moviereview_api.converter.movie.genre;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GenreConverter implements IGenreConverter{
    private final ModelMapper modelMapper;

    public GenreConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ModelMapper getMapper() {
        return modelMapper;
    }
}
