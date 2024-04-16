package com.system.ong.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Date;
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
@Table(name="envio")
public class Envio {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private Date fecha_salida;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Refugio refugio;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Sede sede;
    
    @OneToOne(mappedBy = "envio")
    private EnvioHumanitario envio_humanitario;
    
    @OneToOne(mappedBy = "envio")
    private EnvioMaterial envio_material;

}
