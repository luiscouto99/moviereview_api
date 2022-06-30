package mindswap.academy.moviereview_api.service.movie.director;

import lombok.RequiredArgsConstructor;
import mindswap.academy.moviereview_api.command.movie.director.DirectorDto;
import mindswap.academy.moviereview_api.command.movie.director.DirectorUpdateDto;
import mindswap.academy.moviereview_api.converter.movie.director.IDirectorConverter;
import mindswap.academy.moviereview_api.exception.NotFound;
import mindswap.academy.moviereview_api.persistence.model.movie.director.Director;
import mindswap.academy.moviereview_api.persistence.repository.movie.director.IDirectorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectorService implements IDirectorService {
    private final IDirectorRepository directorRepository;
    private final IDirectorConverter directorConverter;

    @Override
    public List<DirectorDto> getAll() {
        List<Director> directorList = this.directorRepository.findAll();
        return this.directorConverter.converterList(directorList, DirectorDto.class);
    }

    @Override
    public DirectorDto add(DirectorDto directorDto) {
        Director director = this.directorConverter.converter(directorDto, Director.class);
        Director savedDirector = this.directorRepository.save(director);
        return this.directorConverter.converter(savedDirector, DirectorDto.class);
    }

    @Override
    public ResponseEntity<Object> delete(Long id) {
        return null;
    }

    @Override
    public DirectorDto update(Long id, DirectorUpdateDto directorUpdateDto) {
        Director oldDirector = this.directorRepository.findById(id).orElseThrow(() -> new NotFound("Director not found"));
        Director updatedDirector = this.directorRepository.save(this.directorConverter.converterUpdate(directorUpdateDto, oldDirector));
        return this.directorConverter.converter(updatedDirector, DirectorDto.class);
    }
}
