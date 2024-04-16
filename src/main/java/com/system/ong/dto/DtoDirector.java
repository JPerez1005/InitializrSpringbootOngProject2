package com.system.ong.dto;

import com.system.ong.models.Persona;
import lombok.Data;

/**
 * @author perez
 */
@Data
public class DtoDirector {
    private Long id;
    private DtoPersona persona;
}
