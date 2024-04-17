package com.system.ong.drivers;

import com.system.ong.dto.DtoSocio;
import com.system.ong.dto.DtoVoluntario;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.service.ServiceVoluntario;
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
@RequestMapping("/voluntario")
public class ControllerVoluntario {

    @Autowired ServiceVoluntario sv;
    
    @PostMapping("/agregar")
    public ResponseEntity guardarVoluntario(@RequestBody DtoVoluntario dpa,
            @RequestParam Long idPersona,
            @RequestParam Long idSede){
        ResponseEntity mensaje=sv.create(dpa,idPersona,idSede);
        return mensaje;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<DtoVoluntario>> listarVoluntarios() 
            throws EmptyDataException,NoAuthorizedException{
        List<DtoVoluntario> v=(List<DtoVoluntario>) sv.getAll();
        return new ResponseEntity<>(v,HttpStatus.OK);
    }
}
