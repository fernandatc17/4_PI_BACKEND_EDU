package com.tecsup.EduconnectBackEnd.services;

import com.tecsup.EduconnectBackEnd.models.Evento;
import com.tecsup.EduconnectBackEnd.models.daos.EventoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EventoServiceImpl implements EventoService{


    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }

    @Override
    public Evento findById(Long id) {
        return eventoRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Evento save(Evento evento) {
        return eventoRepository.save(evento);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        eventoRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return eventoRepository.existsById(id);
    }
}
