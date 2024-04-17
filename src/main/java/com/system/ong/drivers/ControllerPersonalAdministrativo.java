package com.system.ong.drivers;

import com.system.ong.service.ServicePersonalAdministrativo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author perez
 */
@RestController
@RequestMapping("/personal_administrativo")
public class ControllerPersonalAdministrativo {
    
    @Autowired ServicePersonalAdministrativo spa;
    
}
