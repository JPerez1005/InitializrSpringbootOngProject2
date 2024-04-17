package com.system.ong.drivers;

import com.system.ong.dto.DtoEnvio;
import com.system.ong.dto.DtoVoluntario;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.service.ServiceEnvio;
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
@RequestMapping("/envio")
public class ControllerEnvio {

    @Autowired ServiceEnvio se;
    
    @PostMapping("/agregar")
    public ResponseEntity guardarEnvio(@RequestBody DtoEnvio de,
            @RequestParam Long idRefugio,
            @RequestParam Long idSede){
        ResponseEntity mensaje=se.create(de,idRefugio,idSede);
        return mensaje;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<DtoEnvio>> listarEnvios() 
            throws EmptyDataException,NoAuthorizedException{
        List<DtoEnvio> e=(List<DtoEnvio>) se.getAll();
        return new ResponseEntity<>(e,HttpStatus.OK);
    }
    
}
