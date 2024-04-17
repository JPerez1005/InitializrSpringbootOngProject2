package com.system.ong.drivers;

import com.system.ong.dto.DtoSede;
import com.system.ong.dto.DtoSocio;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.service.ServiceSede;
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
@RequestMapping("/sede")
public class ControllerSede {
    
    @Autowired ServiceSede ss;
    
    @PostMapping("/agregar")
    public ResponseEntity guardarSede(@RequestBody DtoSede ds,
            @RequestParam Long idDirector,
            @RequestParam Long idCiudad){
        ResponseEntity mensaje=ss.create(ds,idDirector,idCiudad);
        return mensaje;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<DtoSede>> listarSede() 
            throws EmptyDataException,NoAuthorizedException{
        List<DtoSede> s=(List<DtoSede>) ss.getAll();
        return new ResponseEntity<>(s,HttpStatus.OK);
    }
    
}
