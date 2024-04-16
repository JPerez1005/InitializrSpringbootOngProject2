package com.system.ong.repositories;

import com.system.ong.models.Medicamentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositoryMedicamentos extends JpaRepository<Medicamentos, Long>{
}
