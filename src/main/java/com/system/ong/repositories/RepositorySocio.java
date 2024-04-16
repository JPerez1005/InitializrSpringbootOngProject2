package com.system.ong.repositories;

import com.system.ong.models.Refugio;
import com.system.ong.models.Sede;
import com.system.ong.models.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositorySocio extends JpaRepository<Socio, Long>{
}
