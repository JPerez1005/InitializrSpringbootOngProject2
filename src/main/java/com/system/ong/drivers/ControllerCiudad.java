package com.system.ong.drivers;

import com.system.ong.dto.DtoAlimentos;
import com.system.ong.dto.DtoCiudad;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.service.ServiceCiudad;
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
@RequestMapping("/ciudad")
public class ControllerCiudad {
    
    @Autowired ServiceCiudad sc;
    
    @PostMapping("/agregar")
    public ResponseEntity guardarCiudad(@RequestBody DtoCiudad dc){
        ResponseEntity mensaje=sc.create(dc);
        return mensaje;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<DtoCiudad>> listarCiudades() 
            throws EmptyDataException,NoAuthorizedException{
        List<DtoCiudad> c=(List<DtoCiudad>) sc.getAll();
        return new ResponseEntity<>(c,HttpStatus.OK);
    }
    
}
