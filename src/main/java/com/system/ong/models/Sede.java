package com.system.ong.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
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
@Table(name="sede")
public class Sede {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Director director;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Ciudad ciudad;
    
    @OneToMany(mappedBy = "sede")
    private List<Socio> socios;
    
    @OneToMany(mappedBy = "sede")
    private List<Envio> envios;
    
    @OneToMany(mappedBy = "sede")
    private List<Voluntario> voluntarios;
    
}
