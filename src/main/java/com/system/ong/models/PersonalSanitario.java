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
@Table(name="personal_sanitario")
public class PersonalSanitario {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private String profesion;
    
    private boolean disponibilidad;
    
    private int numero_trabajos_hechos;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Voluntario voluntario;
    
}
