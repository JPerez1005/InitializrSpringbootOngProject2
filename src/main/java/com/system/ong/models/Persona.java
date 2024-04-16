package com.system.ong.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * @author perez
 */

@NamedQuery(name="Persona.findByEmail",query="select p from Persona p where p.email=:email")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicUpdate//solo actualiza las columnas que cambiaron
@DynamicInsert//solo inserta las columnas que no son nulas
@Table(name="persona")
public class Persona {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String numero_identificacion;
    private String telefono;
    private String rol;
    private String email;
    private String password;
    private String status;
    
    @OneToOne(mappedBy = "persona")
    private Socio socio;
    
    @OneToOne(mappedBy = "persona")
    private Director director;
    
    @OneToOne(mappedBy = "persona")
    private Voluntario voluntario;
}
