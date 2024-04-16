package com.system.ong.repositories;

import com.system.ong.models.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositoryDirector extends JpaRepository<Director, Long>{
}
