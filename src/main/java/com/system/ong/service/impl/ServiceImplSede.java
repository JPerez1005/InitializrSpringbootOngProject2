package com.system.ong.service.impl;

import com.system.ong.dto.DtoRefugio;
import com.system.ong.dto.DtoSede;
import com.system.ong.exceptions.Constantes;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.ExceptionUtil;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.mapper.MapperSede;
import com.system.ong.mapper.MapperSocio;
import com.system.ong.models.Ciudad;
import com.system.ong.models.Director;
import com.system.ong.models.Envio;
import com.system.ong.models.EnvioHumanitario;
import com.system.ong.models.Refugio;
import com.system.ong.models.Sede;
import com.system.ong.repositories.RepositoryCiudad;
import com.system.ong.repositories.RepositoryDirector;
import com.system.ong.repositories.RepositoryPersona;
import com.system.ong.repositories.RepositorySede;
import com.system.ong.repositories.RepositorySocio;
import com.system.ong.security.jwt.JwtFilter;
import com.system.ong.service.ServiceSede;
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
public class ServiceImplSede implements ServiceSede<DtoSede>{
    @Autowired private JwtFilter jf;
    
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private MapperSede ms;
    
    @Autowired private RepositorySede rs;
    
    @Autowired private RepositoryDirector rd;
    
    @Autowired private RepositoryCiudad rc;

    @Override
    public List<DtoSede> getAll()
    throws EmptyDataException, NoAuthorizedException {
        if(jf.isAdmin()){
            return usi.getAll(rs, DtoSede.class);
        }
        NoAuthorizedException e=new NoAuthorizedException("403 Sin autorización",
                    "-Revisar Implementación de la sede|| "
                            + "-verificar Filtrados de Jason, roles existentes || "
                            + "-verificar base de datos",HttpStatus.FORBIDDEN);
        throw e;
    }

    @Override
    public ResponseEntity<Optional<DtoSede>> getById(Long id) {
        if(jf.isAdmin()){
            Optional<DtoSede> ds=usi.findById(rs, DtoSede.class, id);
            if(ds.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(ds,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<String> create(DtoSede dto, Long idDirector, Long idCiudad) {
        if(jf.isAdmin()){
            /*Conversion de datos*/
            Director r=usi.convertidorAEntidades(rd, Director.class, idDirector)
                .orElseThrow(()->new EntityNotFoundException
                ("Sede no encontrada"));
           
            Ciudad c=usi.convertidorAEntidades(rc, Ciudad.class, idCiudad)
                .orElseThrow(()->new EntityNotFoundException
                ("Refugio no encontrado"));
            
            Sede s = ms.toEntity(dto);
            s.setCiudad(c);
            s.setDirector(r);
            rs.save(s);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
        }
        return ExceptionUtil.getResponseEntity(Constantes.UNAUTHORIZED_ACCESS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoSede dto, Long idDirector, Long idCiudad) {
        return null;
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        if(jf.isAdmin()){
            Optional<Sede> os=rs.findById(id);
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
