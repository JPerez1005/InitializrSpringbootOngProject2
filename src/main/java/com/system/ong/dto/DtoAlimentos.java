package com.system.ong.dto;

import com.system.ong.models.EnvioMaterial;
import lombok.Data;

/**
 * @author perez
 */
@Data
public class DtoAlimentos {
    private Long id;
    
    private float numero_toneladas;
    
    private DtoEnvioMaterial envio_material;
}
