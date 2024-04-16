package com.system.ong.dto;

import lombok.Data;

/**
 * @author perez
 */
@Data
public class DtoVoluntario {
    private Long id;
    private DtoSede sede;
    private DtoPersona persona;
}
