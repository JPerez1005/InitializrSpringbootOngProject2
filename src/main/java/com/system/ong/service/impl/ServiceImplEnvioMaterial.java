package com.system.ong.service.impl;

import com.system.ong.dto.DtoEnvioMaterial;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.mapper.MapperEnvioHumanitario;
import com.system.ong.mapper.MapperEnvioMaterial;
import com.system.ong.repositories.RepositoryEnvio;
import com.system.ong.repositories.RepositoryEnvioHumanitario;
import com.system.ong.repositories.RepositoryEnvioMaterial;
import com.system.ong.security.jwt.JwtFilter;
import com.system.ong.service.ServiceEnvioMaterial;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author perez
 */
@Service
public class ServiceImplEnvioMaterial implements ServiceEnvioMaterial<DtoEnvioMaterial>{
    @Autowired private JwtFilter jf;
    
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private MapperEnvioMaterial mem;
    
    @Autowired private RepositoryEnvioMaterial rem;
    
    @Autowired private RepositoryEnvio re;

    @Override
    public List<DtoEnvioMaterial> getAll() throws EmptyDataException, NoAuthorizedException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<Optional<DtoEnvioMaterial>> getById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<String> create(DtoEnvioMaterial dto, Long idEnvio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoEnvioMaterial dto, Long idEnvio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
