package com.system.ong.service.impl;

import com.system.ong.dto.DtoSede;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.mapper.MapperSede;
import com.system.ong.mapper.MapperSocio;
import com.system.ong.repositories.RepositoryCiudad;
import com.system.ong.repositories.RepositoryDirector;
import com.system.ong.repositories.RepositoryPersona;
import com.system.ong.repositories.RepositorySede;
import com.system.ong.repositories.RepositorySocio;
import com.system.ong.security.jwt.JwtFilter;
import com.system.ong.service.ServiceSede;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author perez
 */
@Service
public class ServiceImplSede implements ServiceSede<DtoSede>{
    @Autowired private JwtFilter jf;
    
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private MapperSede ms;
    
    @Autowired private RepositorySede rs;
    
    @Autowired private RepositoryDirector rd;
    
    @Autowired private RepositoryCiudad rc;

    @Override
    public List<DtoSede> getAll() throws EmptyDataException, NoAuthorizedException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<Optional<DtoSede>> getById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<String> create(DtoSede dto, Long idDirector, Long idCiudad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoSede dto, Long idDirector, Long idCiudad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
