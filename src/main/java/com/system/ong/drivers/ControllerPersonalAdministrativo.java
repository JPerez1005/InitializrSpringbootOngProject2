package com.system.ong.drivers;

import com.system.ong.dto.DtoEnvioHumanitario;
import com.system.ong.dto.DtoPersonalAdministrativo;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.service.ServicePersonalAdministrativo;
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
@RequestMapping("/personal_administrativo")
public class ControllerPersonalAdministrativo {
    
    @Autowired ServicePersonalAdministrativo spa;
    
    @PostMapping("/agregar")
    public ResponseEntity guardarPersonalAdminstrativo(@RequestBody DtoPersonalAdministrativo dpa,
            @RequestParam Long idVoluntario){
        ResponseEntity mensaje=spa.create(dpa,idVoluntario);
        return mensaje;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<DtoPersonalAdministrativo>> listarPersonalAdministrativo() 
            throws EmptyDataException,NoAuthorizedException{
        List<DtoPersonalAdministrativo> personal=(List<DtoPersonalAdministrativo>) spa.getAll();
        return new ResponseEntity<>(personal,HttpStatus.OK);
    }
    
}
