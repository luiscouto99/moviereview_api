package mindswap.academy.moviereview_api.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IGenericService<Dto, UpdateDto>{
    List<Dto> getAll();
    Dto add(Dto dto);
    ResponseEntity<HttpStatus> delete(Long id);
    Dto update(Long id, UpdateDto updateDto);
}
