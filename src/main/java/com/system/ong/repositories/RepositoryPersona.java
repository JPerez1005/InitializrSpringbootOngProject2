package com.system.ong.repositories;

import com.system.ong.models.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositoryPersona extends JpaRepository<Persona, Long>{
    Persona findByEmail(@Param(("email")) String email);
}
