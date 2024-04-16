package com.system.ong.repositories;

import com.system.ong.models.EnvioHumanitario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositoryEnvioHumanitario extends JpaRepository<EnvioHumanitario, Long>{
}
