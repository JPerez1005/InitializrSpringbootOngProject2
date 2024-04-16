package com.system.ong.repositories;

import com.system.ong.models.Refugio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositoryRefugio extends JpaRepository<Refugio, Long>{
}
