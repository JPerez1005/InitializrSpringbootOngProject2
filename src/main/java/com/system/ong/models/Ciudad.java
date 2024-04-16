package com.system.ong.models;

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
@Table(name="ciudad")
public class Ciudad {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    
    @OneToOne(mappedBy = "ciudad")
    private Sede sede;
    
    @OneToMany(mappedBy = "ciudad")
    private List<Refugio> refugio;
}
