package com.system.ong.repositories;

import com.system.ong.models.Voluntario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositoryVoluntario extends JpaRepository<Voluntario, Long>{
}
