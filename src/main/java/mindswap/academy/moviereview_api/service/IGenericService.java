package mindswap.academy.moviereview_api.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IGenericService<OutDto,InDto,UpdateDto>{
    List<OutDto> getAll();
    OutDto add(InDto dto);
    ResponseEntity<Object> delete(Long id);
    OutDto update(Long id, UpdateDto updateDto);
}
