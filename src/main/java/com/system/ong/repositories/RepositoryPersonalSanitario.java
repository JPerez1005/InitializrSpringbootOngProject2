package com.system.ong.repositories;

import com.system.ong.models.PersonalSanitario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositoryPersonalSanitario extends JpaRepository<PersonalSanitario, Long>{
}
