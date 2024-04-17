package com.system.ong.drivers;

import com.system.ong.service.ServiceEnvioHumanitario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author perez
 */
@RestController
@RequestMapping("/envio_humanitario")
public class ControllerEnvioHumanitario {

    @Autowired ServiceEnvioHumanitario seh;
    
}
