package com.system.ong.service.impl;

import com.system.ong.dto.DtoPersonalSanitario;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.mapper.MapperPersonalSanitario;
import com.system.ong.mapper.MapperVoluntario;
import com.system.ong.repositories.RepositoryPersonalSanitario;
import com.system.ong.repositories.RepositorySede;
import com.system.ong.repositories.RepositoryVoluntario;
import com.system.ong.security.jwt.JwtFilter;
import com.system.ong.service.ServicePersonalSanitario;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author perez
 */
@Service
public class ServiceImplPersonalSanitario implements ServicePersonalSanitario<DtoPersonalSanitario>{
    @Autowired private JwtFilter jf;
    
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private MapperPersonalSanitario mps;
    
    @Autowired private RepositoryPersonalSanitario rps;
    
    @Autowired private RepositoryVoluntario rv;

    @Override
    public List<DtoPersonalSanitario> getAll() throws EmptyDataException, NoAuthorizedException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<Optional<DtoPersonalSanitario>> getById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<String> create(DtoPersonalSanitario dto, Long idVoluntario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoPersonalSanitario dto, Long idVoluntario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
