package com.system.ong.drivers;

import com.system.ong.dto.DtoAlimentos;
import com.system.ong.dto.DtoPersona;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.service.ServiceAlimentos;
import com.system.ong.service.ServicePersona;
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
@RequestMapping("/alimentos")
public class ControllerAlimentos {
    
    @Autowired ServiceAlimentos sa;
    
    @PostMapping("/agregar")
    public ResponseEntity guardarAlimento(@RequestBody DtoAlimentos da,
            @RequestParam Long idEnvioMaterial){
        ResponseEntity mensaje=sa.create(da,idEnvioMaterial);
        return mensaje;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<DtoAlimentos>> listarAlimentos() 
            throws EmptyDataException,NoAuthorizedException{
        List<DtoAlimentos> alimentos=(List<DtoAlimentos>) sa.getAll();
        return new ResponseEntity<>(alimentos,HttpStatus.OK);
    }
    
}
