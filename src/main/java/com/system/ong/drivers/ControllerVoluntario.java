package com.system.ong.drivers;

import com.system.ong.service.ServiceVoluntario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author perez
 */
@RestController
@RequestMapping("/voluntario")
public class ControllerVoluntario {

    @Autowired ServiceVoluntario sv;
    
}
