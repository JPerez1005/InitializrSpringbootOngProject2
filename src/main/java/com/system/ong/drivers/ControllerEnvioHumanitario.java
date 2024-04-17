package com.system.ong.drivers;

import com.system.ong.dto.DtoEnvioHumanitario;
import com.system.ong.dto.DtoEnvioMaterial;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.service.ServiceEnvioHumanitario;
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
@RequestMapping("/envio_humanitario")
public class ControllerEnvioHumanitario {

    @Autowired ServiceEnvioHumanitario seh;
    
    @PostMapping("/agregar")
    public ResponseEntity guardarEnviosHumanitarios(@RequestBody DtoEnvioHumanitario da,
            @RequestParam Long idEnvio){
        ResponseEntity mensaje=seh.create(da,idEnvio);
        return mensaje;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<DtoEnvioHumanitario>> listarEnviosHumanitarios() 
            throws EmptyDataException,NoAuthorizedException{
        List<DtoEnvioHumanitario> envio_humanitario=(List<DtoEnvioHumanitario>) seh.getAll();
        return new ResponseEntity<>(envio_humanitario,HttpStatus.OK);
    }
    
}
