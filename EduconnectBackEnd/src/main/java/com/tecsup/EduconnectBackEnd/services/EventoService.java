package com.tecsup.EduconnectBackEnd.services;

import com.tecsup.EduconnectBackEnd.models.Evento;
import com.tecsup.EduconnectBackEnd.models.User;

import java.util.List;

public interface EventoService {
    List<Evento> findAll();
    Evento findById(Long id);
    Evento save(Evento evento);
    void delete(Long id);

    boolean existsById(Long id);


}
