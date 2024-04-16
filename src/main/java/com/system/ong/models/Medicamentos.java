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
@Table(name="medicamentos")
public class Medicamentos {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private int unidades;
    
    @OneToOne(cascade = CascadeType.ALL)
    private EnvioMaterial envio_material;
    
}
