package mindswap.academy.moviereview_api.converter.movie.actor;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
@Component
public class ActorConverter implements IActorConverter{
   private final ModelMapper modelMapper;

    public ActorConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setSkipNullEnabled(true);
    }
    @Override
    public ModelMapper getMapper() {
        return modelMapper;
    }
}
