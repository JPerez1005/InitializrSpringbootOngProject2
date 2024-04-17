package com.system.ong.service.impl;

import com.system.ong.dto.DtoMedicamentos;
import com.system.ong.dto.DtoPersonalAdministrativo;
import com.system.ong.exceptions.Constantes;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.ExceptionUtil;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.mapper.MapperPersonalAdministrativo;
import com.system.ong.mapper.MapperPersonalSanitario;
import com.system.ong.models.EnvioHumanitario;
import com.system.ong.models.PersonalAdministrativo;
import com.system.ong.models.PersonalSanitario;
import com.system.ong.models.Voluntario;
import com.system.ong.repositories.RepositoryPersonalAdministrativo;
import com.system.ong.repositories.RepositoryPersonalSanitario;
import com.system.ong.repositories.RepositoryVoluntario;
import com.system.ong.security.jwt.JwtFilter;
import com.system.ong.service.ServicePersonalAdministrativo;
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
public class ServiceImplPersonalAdministrativo implements ServicePersonalAdministrativo<DtoPersonalAdministrativo> {
    @Autowired private JwtFilter jf;
    
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private MapperPersonalAdministrativo mpa;
    
    @Autowired private RepositoryPersonalAdministrativo rpa;
    
    @Autowired private RepositoryVoluntario rv;

    @Override
    public List<DtoPersonalAdministrativo> getAll()
    throws EmptyDataException, NoAuthorizedException {
        if(jf.isAdmin()){
            return usi.getAll(rpa, DtoPersonalAdministrativo.class);
        }
        NoAuthorizedException e=new NoAuthorizedException("403 Sin autorización",
                    "-Revisar Implementación del personal administrativo|| "
                            + "-verificar Filtrados de Jason, roles existentes || "
                            + "-verificar base de datos",HttpStatus.FORBIDDEN);
        throw e;
    }

    @Override
    public ResponseEntity<Optional<DtoPersonalAdministrativo>> getById(Long id) {
        if(jf.isAdmin()){
            Optional<DtoPersonalAdministrativo> dpa=usi.findById(rpa, DtoPersonalAdministrativo.class, id);
            if(dpa.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(dpa,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<String> create(DtoPersonalAdministrativo dto, Long idVoluntario) {
        if(jf.isAdmin()){
            /*Conversion de datos*/
            Voluntario v=usi.convertidorAEntidades(rv, Voluntario.class, idVoluntario)
                .orElseThrow(()->new EntityNotFoundException
                ("voluntario no encontrado"));
            
            PersonalAdministrativo pa = mpa.toEntity(dto);
            pa.setVoluntario(v);
            rpa.save(pa);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
        }
        return ExceptionUtil.getResponseEntity(Constantes.UNAUTHORIZED_ACCESS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoPersonalAdministrativo dto, Long idVoluntario) {
        return null;
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        if(jf.isAdmin()){
            Optional<PersonalAdministrativo> opa=rpa.findById(id);
            if(opa!=null){
                rpa.deleteById(id);
                return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
            }else{
                return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
            }
        }
        return ExceptionUtil.getResponseEntity(Constantes.UNAUTHORIZED_ACCESS, HttpStatus.OK);
    }
}
