package com.system.ong.drivers;

import com.system.ong.service.ServiceSede;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author perez
 */
@RestController
@RequestMapping("/sede")
public class ControllerSede {
    
    @Autowired ServiceSede ss;
    
}
