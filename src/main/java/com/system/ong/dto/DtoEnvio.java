package com.system.ong.dto;

import java.util.Date;
import lombok.Data;

/**
 * @author perez
 */
@Data
public class DtoEnvio {
    private Long id;
    
    private Date fecha_salida;
    
    private DtoRefugio refugio;
    
    private DtoSede sede;
}
