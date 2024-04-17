package com.system.ong.service.impl;

import com.system.ong.dto.DtoEnvioMaterial;
import com.system.ong.dto.DtoPersonalSanitario;
import com.system.ong.dto.DtoRefugio;
import com.system.ong.exceptions.Constantes;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.ExceptionUtil;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.mapper.MapperRefugio;
import com.system.ong.mapper.MapperSede;
import com.system.ong.models.Alimentos;
import com.system.ong.models.Ciudad;
import com.system.ong.models.EnvioHumanitario;
import com.system.ong.models.EnvioMaterial;
import com.system.ong.models.Refugio;
import com.system.ong.repositories.RepositoryCiudad;
import com.system.ong.repositories.RepositoryDirector;
import com.system.ong.repositories.RepositoryRefugio;
import com.system.ong.repositories.RepositorySede;
import com.system.ong.security.jwt.JwtFilter;
import com.system.ong.service.ServiceRefugio;
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
public class ServiceImplRefugio implements ServiceRefugio<DtoRefugio>{
    @Autowired private JwtFilter jf;
    
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private MapperRefugio mr;
    
    @Autowired private RepositoryRefugio rr;
    
    @Autowired private RepositoryCiudad rc;

    @Override
    public List<DtoRefugio> getAll()
    throws EmptyDataException, NoAuthorizedException {
        if(jf.isAdmin()){
            return usi.getAll(rr, DtoRefugio.class);
        }
        NoAuthorizedException e=new NoAuthorizedException("403 Sin autorización",
                    "-Revisar Implementación del refugio|| "
                            + "-verificar Filtrados de Jason, roles existentes || "
                            + "-verificar base de datos",HttpStatus.FORBIDDEN);
        throw e;
    }

    @Override
    public ResponseEntity<Optional<DtoRefugio>> getById(Long id) {
        if(jf.isAdmin()){
            Optional<DtoRefugio> dr=usi.findById(rr, DtoRefugio.class, id);
            if(dr.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(dr,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<String> create(DtoRefugio dto, Long idCiudad) {
        if(jf.isAdmin()){
            /*Conversion de datos*/
            Ciudad c=usi.convertidorAEntidades(rc, Ciudad.class, idCiudad)
                .orElseThrow(()->new EntityNotFoundException
                ("Ciudad no encontrada"));
            
            Refugio r = mr.toEntity(dto);
            r.setCiudad(c);
            rr.save(r);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
        }
        return ExceptionUtil.getResponseEntity(Constantes.UNAUTHORIZED_ACCESS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoRefugio dto, Long idCiudad) {
        return null;
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        if(jf.isAdmin()){
            Optional<Refugio> or=rr.findById(id);
            if(or!=null){
                rr.deleteById(id);
                return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
            }else{
                return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
            }
        }
        return ExceptionUtil.getResponseEntity(Constantes.UNAUTHORIZED_ACCESS, HttpStatus.OK);
    }
}
