package com.system.ong.service.impl;

import com.system.ong.dto.DtoSocio;
import com.system.ong.dto.DtoVoluntario;
import com.system.ong.exceptions.Constantes;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.ExceptionUtil;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.mapper.MapperEnvio;
import com.system.ong.mapper.MapperVoluntario;
import com.system.ong.models.Persona;
import com.system.ong.models.Sede;
import com.system.ong.models.Socio;
import com.system.ong.models.Voluntario;
import com.system.ong.repositories.RepositoryEnvio;
import com.system.ong.repositories.RepositoryPersona;
import com.system.ong.repositories.RepositorySede;
import com.system.ong.repositories.RepositoryVoluntario;
import com.system.ong.security.jwt.JwtFilter;
import com.system.ong.service.ServiceVoluntario;
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
public class ServiceImplVoluntario implements ServiceVoluntario<DtoVoluntario>{
    @Autowired private JwtFilter jf;
    
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private MapperVoluntario mv;
    
    @Autowired private RepositoryVoluntario rv;
    
    @Autowired private RepositorySede rs;
    
    @Autowired private RepositoryPersona rp;

    @Override
    public List<DtoVoluntario> getAll()
    throws EmptyDataException, NoAuthorizedException {
        if(jf.isAdmin()){
            return usi.getAll(rv, DtoVoluntario.class);
        }
        NoAuthorizedException e=new NoAuthorizedException("403 Sin autorización",
                    "-Revisar Implementación del voluntario|| "
                            + "-verificar Filtrados de Jason, roles existentes || "
                            + "-verificar base de datos",HttpStatus.FORBIDDEN);
        throw e;
    }

    @Override
    public ResponseEntity<Optional<DtoVoluntario>> getById(Long id) {
        if(jf.isAdmin()){
            Optional<DtoVoluntario> dv=usi.findById(rv, DtoVoluntario.class, id);
            if(dv.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(dv,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<String> create(DtoVoluntario dto, Long idPersona, Long idSede) {
        if(jf.isAdmin()){
            /*Conversion de datos*/
            Persona p=usi.convertidorAEntidades(rp, Persona.class, idPersona)
                .orElseThrow(()->new EntityNotFoundException
                ("Persona no encontrada"));
           
            Sede s=usi.convertidorAEntidades(rs, Sede.class, idSede)
                .orElseThrow(()->new EntityNotFoundException
                ("Sede no encontrada"));
            
            Voluntario v = mv.toEntity(dto);
            v.setPersona(p);
            v.setSede(s);
            rv.save(v);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
        }
        return ExceptionUtil.getResponseEntity(Constantes.UNAUTHORIZED_ACCESS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoVoluntario dto, Long idPersona, Long idSede) {
        return null;
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        if(jf.isAdmin()){
            Optional<Voluntario> ov=rv.findById(id);
            if(ov!=null){
                rv.deleteById(id);
                return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
            }else{
                return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
            }
        }
        return ExceptionUtil.getResponseEntity(Constantes.UNAUTHORIZED_ACCESS, HttpStatus.OK);
    }
}
