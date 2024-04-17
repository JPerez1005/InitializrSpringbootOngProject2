package com.system.ong.service.impl;

import com.system.ong.dto.DtoAlimentos;
import com.system.ong.dto.DtoCiudad;
import com.system.ong.dto.DtoEnvioMaterial;
import com.system.ong.exceptions.Constantes;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.ExceptionUtil;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.mapper.MapperAlimentos;
import com.system.ong.models.Alimentos;
import com.system.ong.models.Ciudad;
import com.system.ong.models.EnvioMaterial;
import com.system.ong.repositories.RepositoryAlimentos;
import com.system.ong.repositories.RepositoryEnvioMaterial;
import com.system.ong.security.jwt.JwtFilter;
import com.system.ong.service.ServiceAlimentos;
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
public class ServiceImplAlimentos implements ServiceAlimentos<DtoAlimentos>{
    
    @Autowired private JwtFilter jf;
    
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private MapperAlimentos ma;
    
    @Autowired private RepositoryAlimentos ra;
    
    @Autowired private RepositoryEnvioMaterial rem;
    
    @Override
    public List<DtoAlimentos> getAll() throws EmptyDataException, NoAuthorizedException {
        if(jf.isAdmin()){
            return usi.getAll(ra, DtoAlimentos.class);
        }
        NoAuthorizedException e=new NoAuthorizedException("403 Sin autorización",
                    "-Revisar Implementación de los alimentos || "
                            + "-verificar Filtrados de Jason, roles existentes || "
                            + "-verificar base de datos",HttpStatus.FORBIDDEN);
        throw e;
    }

    @Override
    public ResponseEntity<Optional<DtoAlimentos>> getById(Long id) {
        if(jf.isAdmin()){
            Optional<DtoAlimentos> da=usi.findById(ra, DtoAlimentos.class, id);
            if(da.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(da,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<String> create(DtoAlimentos dto, Long idEnvioMaterial) {
        if(jf.isAdmin()){
            /*Conversion de datos*/
            EnvioMaterial em=usi.convertidorAEntidades(rem, DtoEnvioMaterial.class, idEnvioMaterial)
                .orElseThrow(()->new EntityNotFoundException
                ("Envio material no encontrado"));
            
            Alimentos a = ma.toEntity(dto);
            a.setEnvio_material(em);
            ra.save(a);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
        }
        return ExceptionUtil.getResponseEntity(Constantes.UNAUTHORIZED_ACCESS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoAlimentos dto, Long idEnvioMaterial) {
        return null;
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        if(jf.isAdmin()){
            Optional<Alimentos> oa=ra.findById(id);
            if(oa!=null){
                ra.deleteById(id);
                return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
            }else{
                return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
            }
        }
        return ExceptionUtil.getResponseEntity(Constantes.UNAUTHORIZED_ACCESS, HttpStatus.OK);
    }

}
