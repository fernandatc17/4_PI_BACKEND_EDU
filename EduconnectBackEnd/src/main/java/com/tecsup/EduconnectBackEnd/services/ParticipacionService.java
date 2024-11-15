package com.tecsup.EduconnectBackEnd.services;

import com.tecsup.EduconnectBackEnd.models.ParticipacionEvento;

import java.util.List;

public interface ParticipacionService {
    List<ParticipacionEvento> findAll();
    ParticipacionEvento findById(Long id);
    ParticipacionEvento save(ParticipacionEvento participacion);
    void delete(Long id);

    List<ParticipacionEvento> findByEventoId(Long eventoId);
}
