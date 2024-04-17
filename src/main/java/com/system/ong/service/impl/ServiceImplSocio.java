package com.system.ong.service.impl;

import com.system.ong.dto.DtoSede;
import com.system.ong.dto.DtoSocio;
import com.system.ong.exceptions.Constantes;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.ExceptionUtil;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.mapper.MapperSocio;
import com.system.ong.models.Ciudad;
import com.system.ong.models.Director;
import com.system.ong.models.Persona;
import com.system.ong.models.Sede;
import com.system.ong.models.Socio;
import com.system.ong.repositories.RepositoryPersona;
import com.system.ong.repositories.RepositorySede;
import com.system.ong.repositories.RepositorySocio;
import com.system.ong.security.jwt.JwtFilter;
import com.system.ong.service.ServiceSocio;
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
public class ServiceImplSocio implements ServiceSocio<DtoSocio>{
    @Autowired private JwtFilter jf;
    
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private MapperSocio ms;
    
    @Autowired private RepositorySocio rs;
    
    @Autowired private RepositorySede rse;
    
    @Autowired private RepositoryPersona rp;

    @Override
    public List<DtoSocio> getAll()
    throws EmptyDataException, NoAuthorizedException {
        if(jf.isAdmin()){
            return usi.getAll(rs, DtoSocio.class);
        }
        NoAuthorizedException e=new NoAuthorizedException("403 Sin autorización",
                    "-Revisar Implementación del socio|| "
                            + "-verificar Filtrados de Jason, roles existentes || "
                            + "-verificar base de datos",HttpStatus.FORBIDDEN);
        throw e;
    }

    @Override
    public ResponseEntity<Optional<DtoSocio>> getById(Long id) {
        if(jf.isAdmin()){
            Optional<DtoSocio> ds=usi.findById(rs, DtoSocio.class, id);
            if(ds.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(ds,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<String> create(DtoSocio dto, Long idPersona, Long idSede) {
        if(jf.isAdmin()){
            /*Conversion de datos*/
            Persona p=usi.convertidorAEntidades(rp, Persona.class, idPersona)
                .orElseThrow(()->new EntityNotFoundException
                ("Persona no encontrada"));
           
            Sede se=usi.convertidorAEntidades(rse, Sede.class, idSede)
                .orElseThrow(()->new EntityNotFoundException
                ("Sede no encontrado"));
            
            Socio s = ms.toEntity(dto);
            s.setPersona(p);
            s.setSede(se);
            rs.save(s);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
        }
        return ExceptionUtil.getResponseEntity(Constantes.UNAUTHORIZED_ACCESS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoSocio dto, Long idDirector, Long idSede) {
        return null;
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        if(jf.isAdmin()){
            Optional<Socio> os=rs.findById(id);
            if(os!=null){
                rs.deleteById(id);
                return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
            }else{
                return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
            }
        }
        return ExceptionUtil.getResponseEntity(Constantes.UNAUTHORIZED_ACCESS, HttpStatus.OK);
    }
}
