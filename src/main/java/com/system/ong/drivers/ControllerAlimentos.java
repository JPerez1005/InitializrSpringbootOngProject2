package com.system.ong.drivers;

import com.system.ong.service.ServiceAlimentos;
import com.system.ong.service.ServicePersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author perez
 */
@RestController
@RequestMapping("/alimentos")
public class ControllerAlimentos {
    
    @Autowired ServiceAlimentos sa;
    
}
