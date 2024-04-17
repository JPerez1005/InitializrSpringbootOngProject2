package com.system.ong.drivers;

import com.system.ong.dto.DtoAlimentos;
import com.system.ong.dto.DtoMedicamentos;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.service.ServiceMedicamentos;
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
@RequestMapping("/medicamentos")
public class ControllerMedicamentos {
    
    @Autowired ServiceMedicamentos sm;
    
    @PostMapping("/agregar")
    public ResponseEntity guardarMedicamento(@RequestBody DtoMedicamentos da,
            @RequestParam Long idEnvioMaterial){
        ResponseEntity mensaje=sm.create(da,idEnvioMaterial);
        return mensaje;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<DtoMedicamentos>> listarMedicamentos() 
            throws EmptyDataException,NoAuthorizedException{
        List<DtoMedicamentos> medicamentos=(List<DtoMedicamentos>) sm.getAll();
        return new ResponseEntity<>(medicamentos,HttpStatus.OK);
    }
    
}
