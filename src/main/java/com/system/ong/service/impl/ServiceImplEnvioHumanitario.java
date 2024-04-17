package com.system.ong.service.impl;

import com.system.ong.dto.DtoEnvioHumanitario;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.mapper.MapperEnvioHumanitario;
import com.system.ong.mapper.MapperPersonalAdministrativo;
import com.system.ong.repositories.RepositoryEnvio;
import com.system.ong.repositories.RepositoryEnvioHumanitario;
import com.system.ong.repositories.RepositoryPersonalAdministrativo;
import com.system.ong.repositories.RepositoryVoluntario;
import com.system.ong.security.jwt.JwtFilter;
import com.system.ong.service.ServiceEnvioHumanitario;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author perez
 */
@Service
public class ServiceImplEnvioHumanitario implements ServiceEnvioHumanitario<DtoEnvioHumanitario>{
    @Autowired private JwtFilter jf;
    
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private MapperEnvioHumanitario meh;
    
    @Autowired private RepositoryEnvioHumanitario reh;
    
    @Autowired private RepositoryEnvio re;

    @Override
    public List<DtoEnvioHumanitario> getAll() throws EmptyDataException, NoAuthorizedException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<Optional<DtoEnvioHumanitario>> getById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<String> create(DtoEnvioHumanitario dto, Long idEnvio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoEnvioHumanitario dto, Long idEnvio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
