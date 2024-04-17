package com.system.ong.service.impl;

import com.system.ong.dto.DtoEnvioMaterial;
import com.system.ong.dto.DtoMedicamentos;
import com.system.ong.exceptions.Constantes;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.ExceptionUtil;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.mapper.MapperEnvioMaterial;
import com.system.ong.mapper.MapperMedicamentos;
import com.system.ong.models.Alimentos;
import com.system.ong.models.EnvioHumanitario;
import com.system.ong.models.EnvioMaterial;
import com.system.ong.models.Medicamentos;
import com.system.ong.repositories.RepositoryEnvio;
import com.system.ong.repositories.RepositoryEnvioMaterial;
import com.system.ong.repositories.RepositoryMedicamentos;
import com.system.ong.security.jwt.JwtFilter;
import com.system.ong.service.ServiceMedicamentos;
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
public class ServiceImplMedicamentos implements ServiceMedicamentos<DtoMedicamentos>{
    @Autowired private JwtFilter jf;
    
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private MapperMedicamentos mm;
    
    @Autowired private RepositoryMedicamentos rm;
    
    @Autowired private RepositoryEnvioMaterial rem;

    @Override
    public List<DtoMedicamentos> getAll()
    throws EmptyDataException, NoAuthorizedException {
        if(jf.isAdmin()){
            return usi.getAll(rm, DtoMedicamentos.class);
        }
        NoAuthorizedException e=new NoAuthorizedException("403 Sin autorización",
                    "-Revisar Implementación de los medicamentos|| "
                            + "-verificar Filtrados de Jason, roles existentes || "
                            + "-verificar base de datos",HttpStatus.FORBIDDEN);
        throw e;
    }

    @Override
    public ResponseEntity<Optional<DtoMedicamentos>> getById(Long id) {
        if(jf.isAdmin()){
            Optional<DtoMedicamentos> dm=usi.findById(rm, DtoMedicamentos.class, id);
            if(dm.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(dm,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<String> create(DtoMedicamentos dto, Long idEnvioMaterial) {
        if(jf.isAdmin()){
            /*Conversion de datos*/
            EnvioMaterial em=usi.convertidorAEntidades(rem, DtoEnvioMaterial.class, idEnvioMaterial)
                .orElseThrow(()->new EntityNotFoundException
                ("Envio material no encontrado"));
            
            Medicamentos m = mm.toEntity(dto);
            m.setEnvio_material(em);
            rm.save(m);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
        }
        return ExceptionUtil.getResponseEntity(Constantes.UNAUTHORIZED_ACCESS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoMedicamentos dto, Long idEnvioMaterial) {
        return null;
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        if(jf.isAdmin()){
            Optional<Medicamentos> om=rm.findById(id);
            if(om!=null){
                rm.deleteById(id);
                return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
            }else{
                return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
            }
        }
        return ExceptionUtil.getResponseEntity(Constantes.UNAUTHORIZED_ACCESS, HttpStatus.OK);
    }
    
    
}
