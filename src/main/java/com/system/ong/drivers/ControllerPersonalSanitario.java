package com.system.ong.drivers;

import com.system.ong.dto.DtoPersonalAdministrativo;
import com.system.ong.dto.DtoPersonalSanitario;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.service.ServicePersonalSanitario;
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
@RequestMapping("/personal_sanitario")
public class ControllerPersonalSanitario {

    @Autowired ServicePersonalSanitario sps;
    
    @PostMapping("/agregar")
    public ResponseEntity guardarPersonalSanitario(@RequestBody DtoPersonalSanitario dpa,
            @RequestParam Long idVoluntario){
        ResponseEntity mensaje=sps.create(dpa,idVoluntario);
        return mensaje;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<DtoPersonalSanitario>> listarPersonalSanitario() 
            throws EmptyDataException,NoAuthorizedException{
        List<DtoPersonalSanitario> personal=(List<DtoPersonalSanitario>) sps.getAll();
        return new ResponseEntity<>(personal,HttpStatus.OK);
    }
    
}
