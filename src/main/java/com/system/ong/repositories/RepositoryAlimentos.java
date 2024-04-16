package com.system.ong.repositories;

import com.system.ong.models.Alimentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositoryAlimentos extends JpaRepository<Alimentos, Long>{
}
