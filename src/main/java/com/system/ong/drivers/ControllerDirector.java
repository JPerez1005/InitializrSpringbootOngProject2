package com.system.ong.drivers;

import com.system.ong.dto.DtoDirector;
import com.system.ong.dto.DtoPersonalSanitario;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.service.ServiceDirector;
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
@RequestMapping("/director")
public class ControllerDirector {
    
    @Autowired ServiceDirector sd;
    
    @PostMapping("/agregar")
    public ResponseEntity guardardirector(@RequestBody DtoDirector dpa,
            @RequestParam Long idPersona){
        ResponseEntity mensaje=sd.create(dpa,idPersona);
        return mensaje;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<DtoDirector>> listarDirectores() 
            throws EmptyDataException,NoAuthorizedException{
        List<DtoDirector> director=(List<DtoDirector>) sd.getAll();
        return new ResponseEntity<>(director,HttpStatus.OK);
    }
    
}
