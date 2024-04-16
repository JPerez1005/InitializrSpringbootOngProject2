package com.system.ong.repositories;

import com.system.ong.models.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositorySede extends JpaRepository<Sede, Long>{
}
