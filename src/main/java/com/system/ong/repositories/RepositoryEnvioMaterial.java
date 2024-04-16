package com.system.ong.repositories;

import com.system.ong.models.EnvioMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositoryEnvioMaterial extends JpaRepository<EnvioMaterial, Long>{
}
