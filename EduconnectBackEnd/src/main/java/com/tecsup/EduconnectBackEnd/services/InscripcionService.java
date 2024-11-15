package com.tecsup.EduconnectBackEnd.services;
import com.tecsup.EduconnectBackEnd.models.Inscripcion;

import java.util.List;

public interface InscripcionService {

    List<Inscripcion> findAll();
    Inscripcion findById(Long id);
    Inscripcion save(Inscripcion inscripcion);
    void delete(Long id);

    List<Inscripcion> findByEventoId(Long eventoId);
}
