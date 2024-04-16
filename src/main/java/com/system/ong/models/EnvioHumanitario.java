package com.system.ong.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author perez
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="envio_humanitario")
public class EnvioHumanitario {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private int numero_voluntarios;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Envio envio;
    
}
