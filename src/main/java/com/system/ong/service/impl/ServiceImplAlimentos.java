package com.system.ong.service.impl;

import com.system.ong.dto.DtoAlimentos;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.mapper.MapperAlimentos;
import com.system.ong.mapper.MapperMedicamentos;
import com.system.ong.repositories.RepositoryAlimentos;
import com.system.ong.repositories.RepositoryEnvioMaterial;
import com.system.ong.repositories.RepositoryMedicamentos;
import com.system.ong.security.jwt.JwtFilter;
import com.system.ong.service.ServiceAlimentos;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author perez
 */
@Service
public class ServiceImplAlimentos implements ServiceAlimentos<DtoAlimentos>{
    
    @Autowired private JwtFilter jf;
    
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private MapperAlimentos ma;
    
    @Autowired private RepositoryAlimentos ra;
    
    @Autowired private RepositoryEnvioMaterial rem;
    
    @Override
    public List<DtoAlimentos> getAll() throws EmptyDataException, NoAuthorizedException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<Optional<DtoAlimentos>> getById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<String> create(DtoAlimentos dto, Long idEnvioMaterial) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoAlimentos dto, Long idEnvioMaterial) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
