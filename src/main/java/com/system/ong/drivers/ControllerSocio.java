package com.system.ong.drivers;

import com.system.ong.dto.DtoDirector;
import com.system.ong.dto.DtoSocio;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.service.ServiceSocio;
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
@RequestMapping("/socio")
public class ControllerSocio {

    @Autowired ServiceSocio ss;
    
    @PostMapping("/agregar")
    public ResponseEntity guardarSocio(@RequestBody DtoSocio ds,
            @RequestParam Long idPersona,
            @RequestParam Long idSede){
        ResponseEntity mensaje=ss.create(ds,idPersona,idSede);
        return mensaje;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<DtoSocio>> listarSocios() 
            throws EmptyDataException,NoAuthorizedException{
        List<DtoSocio> s=(List<DtoSocio>) ss.getAll();
        return new ResponseEntity<>(s,HttpStatus.OK);
    }
    
}
