package com.system.ong.service.impl;

import com.system.ong.dto.DtoCiudad;
import com.system.ong.dto.DtoDirector;
import com.system.ong.dto.DtoEnvioMaterial;
import com.system.ong.exceptions.Constantes;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.ExceptionUtil;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.mapper.MapperDirector;
import com.system.ong.models.Alimentos;
import com.system.ong.models.Director;
import com.system.ong.models.EnvioMaterial;
import com.system.ong.models.Persona;
import com.system.ong.repositories.RepositoryDirector;
import com.system.ong.repositories.RepositoryPersona;
import com.system.ong.security.jwt.JwtFilter;
import com.system.ong.service.ServiceDirector;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        if(jf.isAdmin()){
            return usi.getAll(rd, DtoDirector.class);
        }
        NoAuthorizedException e=new NoAuthorizedException("403 Sin autorización",
                    "-Revisar Implementación del director || "
                            + "-verificar Filtrados de Jason, roles existentes || "
                            + "-verificar base de datos",HttpStatus.FORBIDDEN);
        throw e;
    }

    @Override
    public ResponseEntity<Optional<DtoDirector>> getById(Long id) {
        if(jf.isAdmin()){
            Optional<DtoDirector> dd=usi.findById(rd, DtoDirector.class, id);
            if(dd.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(dd,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<String> create(DtoDirector dto, Long idPersona) {
        if(jf.isAdmin()){
            /*Conversion de datos*/
            Persona p=usi.convertidorAEntidades(rp, Persona.class, idPersona)
                .orElseThrow(()->new EntityNotFoundException
                ("Persona no encontrada"));
            
            Director d = md.toEntity(dto);
            d.setPersona(p);
            rd.save(d);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
        }
        return ExceptionUtil.getResponseEntity(Constantes.UNAUTHORIZED_ACCESS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoDirector dto, Long idPersona) {
        return null;
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        if(jf.isAdmin()){
            Optional<Director> od=rd.findById(id);
            if(od!=null){
                rd.deleteById(id);
                return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
            }else{
                return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
            }
        }
        return ExceptionUtil.getResponseEntity(Constantes.UNAUTHORIZED_ACCESS, HttpStatus.OK);
    }
}
