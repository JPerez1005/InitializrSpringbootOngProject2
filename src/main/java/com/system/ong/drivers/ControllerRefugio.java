package com.system.ong.drivers;

import com.system.ong.dto.DtoRefugio;
import com.system.ong.dto.DtoSocio;
import com.system.ong.exceptions.EmptyDataException;
import com.system.ong.exceptions.NoAuthorizedException;
import com.system.ong.service.ServiceRefugio;
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
@RequestMapping("/refugio")
public class ControllerRefugio {

    @Autowired ServiceRefugio sr;
    
    @PostMapping("/agregar")
    public ResponseEntity guardarRefugio(@RequestBody DtoRefugio dr,
            @RequestParam Long idCiudad){
        ResponseEntity mensaje=sr.create(dr,idCiudad);
        return mensaje;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<DtoRefugio>> listarRefugios()
            throws EmptyDataException,NoAuthorizedException{
        List<DtoRefugio> r=(List<DtoRefugio>) sr.getAll();
        return new ResponseEntity<>(r,HttpStatus.OK);
    }
}
