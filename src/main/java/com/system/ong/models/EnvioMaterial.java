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
@Table(name="envio_material")
public class EnvioMaterial {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Envio envio;
    
    @OneToOne(mappedBy = "envio_material")
    private Medicamentos medicamentos;
    
    @OneToOne(mappedBy = "envio_material")
    private Alimentos alimentos;
    
}
