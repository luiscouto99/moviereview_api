package mindswap.academy.moviereview_api.service.movie.actor;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.command.movie.actor.ActorDto;
import mindswap.academy.moviereview_api.command.movie.actor.ActorUpdateDto;
import mindswap.academy.moviereview_api.converter.movie.actor.IActorConverter;
import mindswap.academy.moviereview_api.exception.ConflictException;
import mindswap.academy.moviereview_api.exception.NotFoundException;
import mindswap.academy.moviereview_api.persistence.model.movie.actor.Actor;
import mindswap.academy.moviereview_api.persistence.model.movie.director.Director;
import mindswap.academy.moviereview_api.persistence.repository.movie.actor.IActorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static mindswap.academy.moviereview_api.exception.ExceptionMessages.*;

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
        this.actorRepository.checkIfActorIsBeingUsed(id)
                .ifPresent((writer) -> {
                    throw new ConflictException(ACTOR_IS_BEING_USED);
                });
       Actor actor = this.actorRepository.findById(id).orElseThrow(() -> new NotFoundException(ACTOR_NOT_FOUND));
        this.actorRepository.delete(actor);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ActorDto update(Long id, ActorUpdateDto actor) {
        Actor oldActor = this.actorRepository.findById(id).orElseThrow(() -> new NotFoundException("Actor not found"));
        Actor updatedActor = this.actorRepository.save(this.actorConverter.converterUpdate(actor, oldActor));
        return this.actorConverter.converter(updatedActor,ActorDto.class);
    }
}
