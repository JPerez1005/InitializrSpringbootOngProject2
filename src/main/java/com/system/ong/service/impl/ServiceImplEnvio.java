package com.system.ong.service.impl;

import com.system.ong.dto.DtoEnvio;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.mapper.MapperEnvio;
import com.system.ong.mapper.MapperRefugio;
import com.system.ong.repositories.RepositoryEnvio;
import com.system.ong.repositories.RepositoryRefugio;
import com.system.ong.repositories.RepositorySede;
import com.system.ong.security.jwt.JwtFilter;
import com.system.ong.service.ServiceEnvio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author perez
 */
@Service
public class ServiceImplEnvio implements ServiceEnvio<DtoEnvio>{
    @Autowired private JwtFilter jf;
    
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private MapperEnvio me;
    
    @Autowired private RepositoryEnvio re;
    
    @Autowired private RepositorySede rs;
    
    @Autowired private RepositoryRefugio rr;

    @Override
    public List<DtoEnvio> getAll() throws EmptyDataException, NoAuthorizedException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<Optional<DtoEnvio>> getById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<String> create(DtoEnvio dto, Long idRefugio, Long idSede) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoEnvio dto, Long idRefugio, Long idSede) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
