package com.tecsup.EduconnectBackEnd.models.daos;

import com.tecsup.EduconnectBackEnd.models.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriaDao extends JpaRepository<Auditoria, Long> {
}
