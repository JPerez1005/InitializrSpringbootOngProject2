package com.system.ong.repositories;

import com.system.ong.models.PersonalAdministrativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositoryPersonalAdministrativo extends JpaRepository<PersonalAdministrativo, Long>{
}
