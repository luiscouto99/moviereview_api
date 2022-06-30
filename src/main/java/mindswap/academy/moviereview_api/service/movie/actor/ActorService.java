package mindswap.academy.moviereview_api.service.movie.actor;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.exception.command.movie.actor.ActorDto;
import mindswap.academy.moviereview_api.exception.command.movie.actor.ActorUpdateDto;
import mindswap.academy.moviereview_api.converter.movie.actor.IActorConverter;
import mindswap.academy.moviereview_api.exception.NotFound;
import mindswap.academy.moviereview_api.persistence.model.movie.actor.Actor;
import mindswap.academy.moviereview_api.persistence.repository.movie.actor.IActorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ActorService implements IActorService {
    private final IActorRepository actorRepository;
    private final IActorConverter actorConverter;

    @Override
    public List<ActorDto> getAll() {
        List<Actor> actorList = this.actorRepository.findAll();
        return this.actorConverter.converterList(actorList, ActorDto.class);
    }

    @Override
    public ActorDto add(ActorDto actorDto) {
        Actor actor = this.actorConverter.converter(actorDto, Actor.class);
        Actor savedActor = this.actorRepository.save(actor);
        return this.actorConverter.converter(savedActor, ActorDto.class);
    }

    @Override
    public ResponseEntity<Object> delete(Long id) {
        return null;
    }

    @Override
    public ActorDto update(Long id, ActorUpdateDto actor) {
        Actor oldActor = this.actorRepository.findById(id).orElseThrow(() -> new NotFound("Actor not found"));
        Actor updatedActor = this.actorRepository.save(this.actorConverter.converterUpdate(actor, oldActor));
        return this.actorConverter.converter(updatedActor,ActorDto.class);
    }
}
