package com.system.ong.service;

import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.NoAuthorizedException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

/**
 * @author perez
 * @param <Dto>
 */
public interface ServicePersona<Dto> {
    List<Dto> getAll() throws EmptyDataException,NoAuthorizedException;
    ResponseEntity<Optional<Dto>> getById(Long id);
    ResponseEntity<String> create(Dto dto);
    ResponseEntity<String> update(Long id, Dto dto);
    ResponseEntity<String> delete(Long id);
    public ResponseEntity<String> ingresar(Map<String, String> requestMap);
}
