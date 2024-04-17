package com.system.ong.drivers;

import com.system.ong.service.ServiceEnvioMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author perez
 */
@RestController
@RequestMapping("/envio_material")
public class ControllerEnvioMaterial {
    
    @Autowired ServiceEnvioMaterial sem;
    
}
