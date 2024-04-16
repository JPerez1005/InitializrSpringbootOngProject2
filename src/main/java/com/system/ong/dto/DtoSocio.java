package com.system.ong.dto;

import java.util.Date;
import lombok.Data;

/**
 * @author perez
 */
@Data
public class DtoSocio {
    private Long id;
    
    private Date fecha_pago;
    
    private String tipo_cuota;
    
    private DtoPersona persona;
    
    private DtoSede sede;
}
