package com.system.ong.drivers;

import com.system.ong.dto.DtoEnvioMaterial;
import com.system.ong.dto.DtoMedicamentos;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.service.ServiceEnvioMaterial;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author perez
 */
@RestController
@RequestMapping("/envio_material")
public class ControllerEnvioMaterial {
    
    @Autowired ServiceEnvioMaterial sem;
    
    @PostMapping("/agregar")
    public ResponseEntity guardarEnviosMateriales(@RequestBody DtoEnvioMaterial da,
            @RequestParam Long idEnvio){
        ResponseEntity mensaje=sem.create(da,idEnvio);
        return mensaje;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<DtoEnvioMaterial>> listarEnviosMateriales() 
            throws EmptyDataException,NoAuthorizedException{
        List<DtoEnvioMaterial> envio_material=(List<DtoEnvioMaterial>) sem.getAll();
        return new ResponseEntity<>(envio_material,HttpStatus.OK);
    }
    
}
