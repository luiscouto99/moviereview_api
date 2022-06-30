package mindswap.academy.moviereview_api.converter.movie.director;

import mindswap.academy.moviereview_api.converter.movie.actor.IActorConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DirectorConverter implements IDirectorConverter {
    private final ModelMapper modelMapper;
    public DirectorConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setSkipNullEnabled(true);
    }
    @Override
    public ModelMapper getMapper() {
        return modelMapper;
    }
}
