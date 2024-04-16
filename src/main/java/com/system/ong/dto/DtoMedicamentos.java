package com.system.ong.dto;

import lombok.Data;

/**
 * @author perez
 */
@Data
public class DtoMedicamentos {
    private Long id;
    
    private int unidades;
    
    private DtoEnvioMaterial envio_material;
}
