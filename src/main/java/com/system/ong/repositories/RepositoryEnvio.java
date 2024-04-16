package com.system.ong.repositories;

import com.system.ong.models.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositoryEnvio extends JpaRepository<Envio, Long>{
}
