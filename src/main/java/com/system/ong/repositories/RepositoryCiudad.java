package com.system.ong.repositories;

import com.system.ong.models.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositoryCiudad extends JpaRepository<Ciudad, Long>{
}
