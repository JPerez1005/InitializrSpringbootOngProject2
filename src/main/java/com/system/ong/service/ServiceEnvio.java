package com.system.ong.service;

import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.NoAuthorizedException;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

/**
 * @author perez
 */
public interface ServiceEnvio<Dto> {
    List<Dto> getAll() throws EmptyDataException,NoAuthorizedException;
    ResponseEntity<Optional<Dto>> getById(Long id);
    ResponseEntity<String> create(Dto dto,Long idRefugio,Long idSede);
    ResponseEntity<String> update(Long id, Dto dto,Long idRefugio,Long idSede);
    ResponseEntity<String> delete(Long id);
}
