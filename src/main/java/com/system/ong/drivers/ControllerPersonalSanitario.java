package com.system.ong.drivers;

import com.system.ong.service.ServicePersonalSanitario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author perez
 */
@RestController
@RequestMapping("/personal_sanitario")
public class ControllerPersonalSanitario {

    @Autowired ServicePersonalSanitario sps;
    
}
