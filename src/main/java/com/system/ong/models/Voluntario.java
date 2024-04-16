package com.system.ong.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@Table(name="voluntario")
public class Voluntario {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Sede sede;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Persona persona;
    
    @OneToOne(mappedBy = "voluntario")
    private PersonalSanitario personal_sanitario;
    
    @OneToOne(mappedBy = "voluntario")
    private PersonalAdministrativo personal_administrativo;
    
}
