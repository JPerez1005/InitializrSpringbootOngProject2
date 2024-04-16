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
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * @author perez
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="socio")
public class Socio {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private Date fecha_pago;
    
    private String tipo_cuota;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Persona persona;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Sede sede;
    
    
}
