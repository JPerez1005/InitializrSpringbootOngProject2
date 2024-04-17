package com.system.ong.service.impl;

import com.system.ong.dto.DtoAlimentos;
import com.system.ong.dto.DtoEnvio;
import com.system.ong.exceptions.Constantes;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.ExceptionUtil;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.mapper.MapperEnvio;
import com.system.ong.mapper.MapperRefugio;
import com.system.ong.models.Alimentos;
import com.system.ong.models.Director;
import com.system.ong.models.Envio;
import com.system.ong.models.Persona;
import com.system.ong.models.Refugio;
import com.system.ong.models.Sede;
import com.system.ong.repositories.RepositoryEnvio;
import com.system.ong.repositories.RepositoryRefugio;
import com.system.ong.repositories.RepositorySede;
import com.system.ong.security.jwt.JwtFilter;
import com.system.ong.service.ServiceEnvio;
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
public class ServiceImplEnvio implements ServiceEnvio<DtoEnvio>{
    @Autowired private JwtFilter jf;
    
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private MapperEnvio me;
    
    @Autowired private RepositoryEnvio re;
    
    @Autowired private RepositorySede rs;
    
    @Autowired private RepositoryRefugio rr;

    @Override
    public List<DtoEnvio> getAll() throws EmptyDataException, NoAuthorizedException {
        if(jf.isAdmin()){
            return usi.getAll(re, DtoEnvio.class);
        }
        NoAuthorizedException e=new NoAuthorizedException("403 Sin autorización",
                    "-Revisar Implementación de los envios || "
                            + "-verificar Filtrados de Jason, roles existentes || "
                            + "-verificar base de datos",HttpStatus.FORBIDDEN);
        throw e;
    }

    @Override
    public ResponseEntity<Optional<DtoEnvio>> getById(Long id) {
        if(jf.isAdmin()){
            Optional<DtoEnvio> de=usi.findById(re, DtoEnvio.class, id);
            if(de.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(de,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<String> create(DtoEnvio dto, Long idRefugio, Long idSede) {
        if(jf.isAdmin()){
            /*Conversion de datos*/
            Sede s=usi.convertidorAEntidades(rs, Sede.class, idSede)
                .orElseThrow(()->new EntityNotFoundException
                ("Sede no encontrada"));
           
            Refugio r=usi.convertidorAEntidades(rr, Refugio.class, idRefugio)
                .orElseThrow(()->new EntityNotFoundException
                ("Refugio no encontrado"));
            
            Envio e = me.toEntity(dto);
            e.setRefugio(r);
            e.setSede(s);
            re.save(e);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
        }
        return ExceptionUtil.getResponseEntity(Constantes.UNAUTHORIZED_ACCESS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoEnvio dto, Long idRefugio, Long idSede) {
        return null;
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        if(jf.isAdmin()){
            Optional<Envio> oe=re.findById(id);
            if(oe!=null){
                re.deleteById(id);
                return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
            }else{
                return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
            }
        }
        return ExceptionUtil.getResponseEntity(Constantes.UNAUTHORIZED_ACCESS, HttpStatus.OK);
    }
}
