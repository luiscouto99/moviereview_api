package mindswap.academy.moviereview_api.service.movie.actor;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.command.movie.actor.ActorDto;
import mindswap.academy.moviereview_api.command.movie.actor.ActorUpdateDto;
import mindswap.academy.moviereview_api.converter.movie.actor.IActorConverter;
import mindswap.academy.moviereview_api.exception.ConflictException;
import mindswap.academy.moviereview_api.exception.NotFoundException;
import mindswap.academy.moviereview_api.persistence.model.movie.actor.Actor;
import mindswap.academy.moviereview_api.persistence.model.movie.director.Director;
import mindswap.academy.moviereview_api.persistence.repository.movie.IMovieRepository;
import mindswap.academy.moviereview_api.persistence.repository.movie.actor.IActorRepository;
import org.springframework.cache.Cache;
import org.springframework.http.HttpStatus;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static mindswap.academy.moviereview_api.exception.ExceptionMessages.*;

@RequiredArgsConstructor
@Service
public class ActorService implements IActorService {
    private final IActorRepository actorRepository;
    private final IActorConverter actorConverter;
    private final CacheManager cacheManager;

    @Override
    @Cacheable("actors")
    public List<ActorDto> getAll() {
        List<Actor> actorList = this.actorRepository.findAll();
        return this.actorConverter.converterList(actorList, ActorDto.class);
    }

    @Override
    public ActorDto add(ActorDto actorDto) {
        Actor actor = this.actorConverter.converter(actorDto, Actor.class);
        clearCacheActorAndMovie();
        Actor savedActor = this.actorRepository.save(actor);
        return this.actorConverter.converter(savedActor, ActorDto.class);
    }

    private void clearCacheActorAndMovie() {
        Cache cacheActors = this.cacheManager.getCache("actors");
        Cache cacheMovies = this.cacheManager.getCache("movies");
        if(cacheActors != null)cacheActors.clear();
        if(cacheMovies != null)cacheMovies.clear();
    }

    @Override
    @CacheEvict(key = "#id", value = "actor")
    public ResponseEntity<Object> delete(Long id) {
        this.actorRepository.checkIfActorIsBeingUsed(id)
                .ifPresent((writer) -> {
                    throw new ConflictException(ACTOR_IS_BEING_USED);
                });
        Actor actor = this.actorRepository.findById(id).orElseThrow(() -> new NotFoundException(ACTOR_NOT_FOUND));
        clearCacheActorAndMovie();
        this.actorRepository.delete(actor);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @CacheEvict(key = "#id", value = "actor")
    public ActorDto update(Long id, ActorUpdateDto actor) {
        Actor oldActor = this.actorRepository.findById(id).orElseThrow(() -> new NotFoundException("Actor not found"));
        clearCacheActorAndMovie();
        Actor updatedActor = this.actorRepository.save(this.actorConverter.converterUpdate(actor, oldActor));
        return this.actorConverter.converter(updatedActor, ActorDto.class);
    }
}
