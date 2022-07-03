package mindswap.academy.moviereview_api.controller.movie.actor;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.command.movie.actor.ActorDto;
import mindswap.academy.moviereview_api.command.movie.actor.ActorUpdateDto;
import mindswap.academy.moviereview_api.service.movie.actor.IActorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/actor")
public class ActorController {
    private final IActorService actorService;

    @GetMapping
    public List<ActorDto> getAll() {
        return this.actorService.getAll();
    }

    @PostMapping
    public ActorDto add(@Valid @RequestBody ActorDto actorDto) {
       return this.actorService.add(actorDto);
    }

    @PutMapping("/{id}")
    public ActorDto update(@PathVariable Long id,@Valid @RequestBody ActorUpdateDto actorUpdateDto) {
        return this.actorService.update(id,actorUpdateDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return this.actorService.delete(id);
    }
}
