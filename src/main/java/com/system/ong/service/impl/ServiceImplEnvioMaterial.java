package com.system.ong.service.impl;

import com.system.ong.dto.DtoEnvio;
import com.system.ong.dto.DtoEnvioMaterial;
import com.system.ong.exceptions.Constantes;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.ExceptionUtil;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.mapper.MapperEnvioHumanitario;
import com.system.ong.mapper.MapperEnvioMaterial;
import com.system.ong.models.Alimentos;
import com.system.ong.models.Envio;
import com.system.ong.models.EnvioHumanitario;
import com.system.ong.models.EnvioMaterial;
import com.system.ong.repositories.RepositoryEnvio;
import com.system.ong.repositories.RepositoryEnvioHumanitario;
import com.system.ong.repositories.RepositoryEnvioMaterial;
import com.system.ong.security.jwt.JwtFilter;
import com.system.ong.service.ServiceEnvioMaterial;
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
public class ServiceImplEnvioMaterial implements ServiceEnvioMaterial<DtoEnvioMaterial>{
    @Autowired private JwtFilter jf;
    
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private MapperEnvioMaterial mem;
    
    @Autowired private RepositoryEnvioMaterial rem;
    
    @Autowired private RepositoryEnvio re;

    @Override
    public List<DtoEnvioMaterial> getAll()
    throws EmptyDataException, NoAuthorizedException {
        if(jf.isAdmin()){
            return usi.getAll(rem, DtoEnvioMaterial.class);
        }
        NoAuthorizedException e=new NoAuthorizedException("403 Sin autorización",
                    "-Revisar Implementación de los envios materiales|| "
                            + "-verificar Filtrados de Jason, roles existentes || "
                            + "-verificar base de datos",HttpStatus.FORBIDDEN);
        throw e;
    }

    @Override
    public ResponseEntity<Optional<DtoEnvioMaterial>> getById(Long id) {
        if(jf.isAdmin()){
            Optional<DtoEnvioMaterial> dem=usi.findById(rem, DtoEnvioMaterial.class, id);
            if(dem.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(dem,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<String> create(DtoEnvioMaterial dto, Long idEnvio) {
        if(jf.isAdmin()){
            /*Conversion de datos*/
            Envio e=usi.convertidorAEntidades(re, DtoEnvio.class, idEnvio)
                .orElseThrow(()->new EntityNotFoundException
                ("Envio material no encontrado"));
            
            EnvioMaterial em = mem.toEntity(dto);
            em.setEnvio(e);
            rem.save(em);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
        }
        return ExceptionUtil.getResponseEntity(Constantes.UNAUTHORIZED_ACCESS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoEnvioMaterial dto, Long idEnvio) {
        return null;
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        if(jf.isAdmin()){
            Optional<EnvioMaterial> oem=rem.findById(id);
            if(oem!=null){
                rem.deleteById(id);
                return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
            }else{
                return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
            }
        }
        return ExceptionUtil.getResponseEntity(Constantes.UNAUTHORIZED_ACCESS, HttpStatus.OK);
    }
}
