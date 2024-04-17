package com.system.ong.service.impl;

import com.system.ong.dto.DtoPersonalAdministrativo;
import com.system.ong.dto.DtoPersonalSanitario;
import com.system.ong.exceptions.Constantes;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.ExceptionUtil;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.mapper.MapperPersonalSanitario;
import com.system.ong.mapper.MapperVoluntario;
import com.system.ong.models.Ciudad;
import com.system.ong.models.EnvioHumanitario;
import com.system.ong.models.PersonalSanitario;
import com.system.ong.models.Refugio;
import com.system.ong.models.Voluntario;
import com.system.ong.repositories.RepositoryPersonalSanitario;
import com.system.ong.repositories.RepositorySede;
import com.system.ong.repositories.RepositoryVoluntario;
import com.system.ong.security.jwt.JwtFilter;
import com.system.ong.service.ServicePersonalSanitario;
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
public class ServiceImplPersonalSanitario implements ServicePersonalSanitario<DtoPersonalSanitario>{
    @Autowired private JwtFilter jf;
    
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private MapperPersonalSanitario mps;
    
    @Autowired private RepositoryPersonalSanitario rps;
    
    @Autowired private RepositoryVoluntario rv;

    @Override
    public List<DtoPersonalSanitario> getAll()
    throws EmptyDataException, NoAuthorizedException {
        if(jf.isAdmin()){
            return usi.getAll(rps, DtoPersonalSanitario.class);
        }
        NoAuthorizedException e=new NoAuthorizedException("403 Sin autorización",
                    "-Revisar Implementación del personal sanitario|| "
                            + "-verificar Filtrados de Jason, roles existentes || "
                            + "-verificar base de datos",HttpStatus.FORBIDDEN);
        throw e;
    }

    @Override
    public ResponseEntity<Optional<DtoPersonalSanitario>> getById(Long id) {
        if(jf.isAdmin()){
            Optional<DtoPersonalSanitario> dps=usi.findById(rps, DtoPersonalSanitario.class, id);
            if(dps.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(dps,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<String> create(DtoPersonalSanitario dto, Long idVoluntario) {
        if(jf.isAdmin()){
            /*Conversion de datos*/
            Voluntario v=usi.convertidorAEntidades(rv, Voluntario.class, idVoluntario)
                .orElseThrow(()->new EntityNotFoundException
                ("voluntario no encontrado"));
            
            PersonalSanitario ps = mps.toEntity(dto);
            ps.setVoluntario(v);
            rps.save(ps);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
        }
        return ExceptionUtil.getResponseEntity(Constantes.UNAUTHORIZED_ACCESS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoPersonalSanitario dto, Long idVoluntario) {
        return null;
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        if(jf.isAdmin()){
            Optional<PersonalSanitario> ops=rps.findById(id);
            if(ops!=null){
                rps.deleteById(id);
                return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
            }else{
                return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
            }
        }
        return ExceptionUtil.getResponseEntity(Constantes.UNAUTHORIZED_ACCESS, HttpStatus.OK);
    }
}
