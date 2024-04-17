package com.system.ong.service.impl;

import com.system.ong.dto.DtoPersona;
import com.system.ong.exceptions.Constantes;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.ExceptionUtil;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.mapper.MapperPersona;
import com.system.ong.models.Persona;
import com.system.ong.repositories.RepositoryPersona;
import com.system.ong.security.CustomerDetailsService;
import com.system.ong.security.jwt.JwtFilter;
import com.system.ong.security.jwt.JwtUtil;
import com.system.ong.service.ServicePersona;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @author perez
 */
@Service
public class ServiceImplPersona implements ServicePersona<DtoPersona>{

    @Autowired private JwtFilter jf;
    
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private RepositoryPersona rp;
    
    @Autowired private MapperPersona mp;
    
    @Autowired private AuthenticationManager authenticationManager;
    
    @Autowired private CustomerDetailsService detallesPersona;
    
    @Autowired private JwtUtil jwtUtil;
    
    @Override
    public List<DtoPersona> getAll()
    throws EmptyDataException,NoAuthorizedException{
        if(jf.isAdmin() || jf.isDirector()){
            return usi.getAll(rp, DtoPersona.class);
        }
        NoAuthorizedException e=new NoAuthorizedException("403 Sin autorización",
                    "-Revisar Implementación de la persona || "
                            + "-verificar Filtrados de Jason, roles existentes || "
                            + "-verificar base de datos",HttpStatus.FORBIDDEN);
        throw e;
    }

    @Override
    public ResponseEntity<Optional<DtoPersona>> getById(Long id) {
        if(jf.isAdmin() || jf.isDirector()){
            if(usi.findById(rp, DtoPersona.class, id).isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(usi.findById(rp, DtoPersona.class, id),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<String> create(DtoPersona dto) {
        if(jf.isAdmin() || jf.isDirector()){
            if(rp.findByEmail(dto.getEmail())!=null){
                return ExceptionUtil.getResponseEntity(Constantes.EXIST_DATA, HttpStatus.CONFLICT);
            }else{
                usi.save(rp, dto, Persona.class);
                return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
            }
        }
        return ExceptionUtil.getResponseEntity(Constantes.UNAUTHORIZED_ACCESS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoPersona dto) {
        if(jf.isAdmin() || jf.isDirector()){
            Optional<Persona> optionalPersona=rp.findById(id);
            if(optionalPersona!=null){
                Persona p=optionalPersona.get();
                p=mp.toEntity(dto);
                p=rp.save(p);
                return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
            }else{
                return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_MODIFIED);
            }
        }
        return ExceptionUtil.getResponseEntity(Constantes.UNAUTHORIZED_ACCESS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        if(jf.isAdmin() || jf.isDirector()){
            Optional<Persona> optionalPersona=rp.findById(id);
            if(optionalPersona!=null){
                rp.deleteById(id);
                return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
            }else{
                return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
            }
        }
        return ExceptionUtil.getResponseEntity(Constantes.UNAUTHORIZED_ACCESS, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<String> ingresar(Map<String, String> requestMap) {
        try {
            Authentication authentication=authenticationManager.authenticate(
                /*autenticamos los datos que nos entregan*/
                new UsernamePasswordAuthenticationToken(requestMap.get("email"),requestMap.get("password"))
            );
            //si se está autenticado...
            if (authentication.isAuthenticated()) {
                if(detallesPersona.getPersonaDetail().getStatus().equalsIgnoreCase("true")){
                    return new ResponseEntity<String>
                        ("{\"token\":\""+jwtUtil.generateToken
                            (detallesPersona.getPersonaDetail().getEmail(),
                            detallesPersona.getPersonaDetail().getRol())
                            +"\"}",HttpStatus.OK);
                }else{
//                    AprobacionAdmin exception=new AprobacionAdmin("","{\"mensaje\":\""
//                            +"Espere la Aprobación del admin"+"\"}",HttpStatus.BAD_REQUEST);
//                    throw exception;
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
//        ErrorValidacion exception=new ErrorValidacion("","{\"mensaje\":\""+"Credenciales Incorrectas"+"\"}"
//                ,HttpStatus.BAD_REQUEST);
//                    throw exception;
        return null;
    }

}
