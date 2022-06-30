package mindswap.academy.moviereview_api.converter.user;

import mindswap.academy.moviereview_api.converter.IConverter;
import mindswap.academy.moviereview_api.persistence.model.user.User;

public interface IUserConverter<Entity, UpdateDto> extends IConverter {
    Entity updateDtoToEntity(UpdateDto updateDto, Entity entity);
}
