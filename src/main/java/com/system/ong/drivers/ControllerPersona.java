package com.system.ong.drivers;

import com.system.ong.dto.DtoPersona;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.service.ServicePersona;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author perez
 */
@RestController
@RequestMapping("/persona")
public class ControllerPersona {
    
    @Autowired ServicePersona sp;
    
    @PostMapping("/agregar_persona")
    public ResponseEntity guardarPersona(@RequestBody DtoPersona dp){
        ResponseEntity mensaje=sp.create(dp);
        return mensaje;
    }
    
    @GetMapping("/listar_personas")
    public ResponseEntity<List<DtoPersona>> listarUsuarios() 
            throws EmptyDataException,NoAuthorizedException{
        List<DtoPersona> usuarios=(List<DtoPersona>) sp.getAll();
        return new ResponseEntity<>(usuarios,HttpStatus.OK);
    }
    
    @PostMapping("/ingresar")
    public ResponseEntity<String> ingreso(@RequestBody(required=true) Map<String,String> requestMap){
        try{
            return sp.ingresar(requestMap);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("algo salió mal logueandose",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
