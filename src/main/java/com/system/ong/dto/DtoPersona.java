package com.system.ong.dto;

import lombok.Data;

/**
 * @author perez
 */
@Data
public class DtoPersona {
    private Long id;
    private String nombre;
    private String numero_identificacion;
    private String telefono;
    private String rol;
    private String email;
    private String password;
    private String status;
}
