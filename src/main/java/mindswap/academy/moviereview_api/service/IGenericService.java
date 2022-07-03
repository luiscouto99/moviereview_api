package mindswap.academy.moviereview_api.service;

import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

public interface IGenericService<OutDto, InDto, UpdateDto>{
    List<OutDto> getAll();
    OutDto add(InDto dto);
    ResponseEntity<Object> delete(Long id);
    OutDto update(Long id, UpdateDto updateDto);
    default void clearCache(CacheManager cacheManager, String name) {
        Objects.requireNonNull(cacheManager.getCache(name)).clear();
        System.out.println("Cache cleared");
    }
}
