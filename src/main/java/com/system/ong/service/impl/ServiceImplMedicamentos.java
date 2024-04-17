package com.system.ong.service.impl;

import com.system.ong.dto.DtoMedicamentos;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.mapper.MapperEnvioMaterial;
import com.system.ong.mapper.MapperMedicamentos;
import com.system.ong.repositories.RepositoryEnvio;
import com.system.ong.repositories.RepositoryEnvioMaterial;
import com.system.ong.repositories.RepositoryMedicamentos;
import com.system.ong.security.jwt.JwtFilter;
import com.system.ong.service.ServiceMedicamentos;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author perez
 */
@Service
public class ServiceImplMedicamentos implements ServiceMedicamentos<DtoMedicamentos>{
    @Autowired private JwtFilter jf;
    
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private MapperMedicamentos mm;
    
    @Autowired private RepositoryMedicamentos rm;
    
    @Autowired private RepositoryEnvioMaterial rem;

    @Override
    public List<DtoMedicamentos> getAll() throws EmptyDataException, NoAuthorizedException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<Optional<DtoMedicamentos>> getById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<String> create(DtoMedicamentos dto, Long idEnvioMaterial) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoMedicamentos dto, Long idEnvioMaterial) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
