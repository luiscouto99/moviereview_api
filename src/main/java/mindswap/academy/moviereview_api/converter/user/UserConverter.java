package mindswap.academy.moviereview_api.converter.user;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements IUserConverter {
    private final ModelMapper MODEL_MAPPER;

    public UserConverter(ModelMapper modelMapper) {
        this.MODEL_MAPPER = modelMapper;
        this.MODEL_MAPPER.getConfiguration().setSkipNullEnabled(true);
    }

    @Override
    public ModelMapper getMapper() {
        return this.MODEL_MAPPER;
    }
}
