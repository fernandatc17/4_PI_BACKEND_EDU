package com.tecsup.EduconnectBackEnd.models.daos;

import com.tecsup.EduconnectBackEnd.models.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    List<Inscripcion> findByEvento_Id(Long eventoId);
}
