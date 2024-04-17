package com.system.ong.service.impl;

import com.system.ong.dto.DtoCiudad;
import com.system.ong.dto.DtoPersona;
import com.system.ong.exceptions.Constantes;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.ExceptionUtil;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.mapper.MapperCiudad;
import com.system.ong.models.Ciudad;
import com.system.ong.models.Persona;
import com.system.ong.repositories.RepositoryCiudad;
import com.system.ong.security.jwt.JwtFilter;
import com.system.ong.service.ServiceCiudad;
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
public class ServiceImplCiudad implements ServiceCiudad<DtoCiudad>{

    @Autowired private JwtFilter jf;
    
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private RepositoryCiudad rc;
    
    @Autowired private MapperCiudad mc;
    
    @Override
    public List<DtoCiudad> getAll()
    throws EmptyDataException, NoAuthorizedException {
        if(jf.isAdmin()){
            return usi.getAll(rc, DtoCiudad.class);
        }
        NoAuthorizedException e=new NoAuthorizedException("403 Sin autorización",
                    "-Revisar Implementación de la ciudad || "
                            + "-verificar Filtrados de Jason, roles existentes || "
                            + "-verificar base de datos",HttpStatus.FORBIDDEN);
        throw e;
    }

    @Override
    public ResponseEntity<Optional<DtoCiudad>> getById(Long id) {
        if(jf.isAdmin()){
            if(usi.findById(rc, DtoCiudad.class, id).isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(usi.findById(rc, DtoCiudad.class, id),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<String> create(DtoCiudad dto) {
        if(jf.isAdmin()){
            usi.save(rc, dto, Ciudad.class);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
        }
        return ExceptionUtil.getResponseEntity(Constantes.UNAUTHORIZED_ACCESS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoCiudad dto) {
        if(jf.isAdmin()){
            Optional<Ciudad> optionalCiudad=rc.findById(id);
            if(optionalCiudad!=null){
                Ciudad c=optionalCiudad.get();
                c=mc.toEntity(dto);
                c=rc.save(c);
                return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
            }else{
                return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_MODIFIED);
            }
        }
        return ExceptionUtil.getResponseEntity(Constantes.UNAUTHORIZED_ACCESS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        if(jf.isAdmin()){
            Optional<Ciudad> optionalCiudad=rc.findById(id);
            if(optionalCiudad!=null){
                rc.deleteById(id);
                return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
            }else{
                return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
            }
        }
        return ExceptionUtil.getResponseEntity(Constantes.UNAUTHORIZED_ACCESS, HttpStatus.OK);
    }

}
