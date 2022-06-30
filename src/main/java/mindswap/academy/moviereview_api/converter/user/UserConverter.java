package mindswap.academy.moviereview_api.converter.user;

import mindswap.academy.moviereview_api.command.user.UserUpdateDto;
import mindswap.academy.moviereview_api.persistence.model.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements IUserConverter<User, UserUpdateDto> {
    private final ModelMapper MODEL_MAPPER;

    public UserConverter(ModelMapper modelMapper) {
        this.MODEL_MAPPER = modelMapper;
        this.MODEL_MAPPER.getConfiguration().setSkipNullEnabled(true);
    }

    @Override
    public ModelMapper getMapper() {
        return this.MODEL_MAPPER;
    }

    @Override
    public User updateDtoToEntity(UserUpdateDto userUpdateDto, User user) {
        this.MODEL_MAPPER.map(userUpdateDto, user);
        return user;
    }
}
