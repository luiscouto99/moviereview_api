package mindswap.academy.moviereview_api.converter.user.role;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter implements IRoleConverter {
    private final ModelMapper MODEL_MAPPER;

    public RoleConverter(ModelMapper modelMapper) {
        this.MODEL_MAPPER = modelMapper;
        this.MODEL_MAPPER.getConfiguration().setSkipNullEnabled(true);
    }

    @Override
    public ModelMapper getMapper() {
        return this.MODEL_MAPPER;
    }
}
