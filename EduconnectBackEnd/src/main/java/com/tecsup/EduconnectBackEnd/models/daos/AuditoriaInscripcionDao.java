package com.tecsup.EduconnectBackEnd.models.daos;


import com.tecsup.EduconnectBackEnd.models.AuditoriaInscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriaInscripcionDao extends JpaRepository<AuditoriaInscripcion, Long> {
}