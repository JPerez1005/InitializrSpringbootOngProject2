package com.system.ong.service.impl;

import com.system.ong.dto.DtoDirector;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.mapper.MapperDirector;
import com.system.ong.repositories.RepositoryDirector;
import com.system.ong.repositories.RepositoryPersona;
import com.system.ong.security.jwt.JwtFilter;
import com.system.ong.service.ServiceDirector;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author perez
 */
@Service
public class ServiceImplDirector implements ServiceDirector<DtoDirector>{
    @Autowired private JwtFilter jf;
    
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private MapperDirector md;
    
    @Autowired private RepositoryDirector rd;
    
    @Autowired private RepositoryPersona rp;
    
    @Override
    public List<DtoDirector> getAll() throws EmptyDataException, NoAuthorizedException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<Optional<DtoDirector>> getById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<String> create(DtoDirector dto, Long idPersona) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoDirector dto, Long idPersona) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
