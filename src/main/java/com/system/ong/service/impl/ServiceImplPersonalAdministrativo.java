package com.system.ong.service.impl;

import com.system.ong.dto.DtoPersonalAdministrativo;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.mapper.MapperPersonalAdministrativo;
import com.system.ong.mapper.MapperPersonalSanitario;
import com.system.ong.repositories.RepositoryPersonalAdministrativo;
import com.system.ong.repositories.RepositoryPersonalSanitario;
import com.system.ong.repositories.RepositoryVoluntario;
import com.system.ong.security.jwt.JwtFilter;
import com.system.ong.service.ServicePersonalAdministrativo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author perez
 */
@Service
public class ServiceImplPersonalAdministrativo implements ServicePersonalAdministrativo<DtoPersonalAdministrativo> {
    @Autowired private JwtFilter jf;
    
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private MapperPersonalAdministrativo mpa;
    
    @Autowired private RepositoryPersonalAdministrativo rpa;
    
    @Autowired private RepositoryVoluntario rv;

    @Override
    public List<DtoPersonalAdministrativo> getAll() throws EmptyDataException, NoAuthorizedException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<Optional<DtoPersonalAdministrativo>> getById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<String> create(DtoPersonalAdministrativo dto, Long idVoluntario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoPersonalAdministrativo dto, Long idVoluntario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
