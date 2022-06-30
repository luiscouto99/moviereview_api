package mindswap.academy.moviereview_api.service.movie.actor;

import mindswap.academy.moviereview_api.command.movie.actor.ActorDto;
import mindswap.academy.moviereview_api.command.movie.actor.ActorUpdateDto;
import mindswap.academy.moviereview_api.service.IGenericService;

public interface IActorService extends IGenericService<ActorDto, ActorDto, ActorUpdateDto> {
}
