package com.tecsup.EduconnectBackEnd.controllers;


import com.tecsup.EduconnectBackEnd.models.Evento;
import com.tecsup.EduconnectBackEnd.models.Inscripcion;
import com.tecsup.EduconnectBackEnd.models.ParticipacionEvento;
import com.tecsup.EduconnectBackEnd.models.User;
import com.tecsup.EduconnectBackEnd.services.EventoService;
import com.tecsup.EduconnectBackEnd.services.InscripcionService;
import com.tecsup.EduconnectBackEnd.services.ParticipacionService;
import com.tecsup.EduconnectBackEnd.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/eventos")
@CrossOrigin(origins = "http://localhost:3000") // Permitir CORS para tu frontend
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private ParticipacionService participacionService; // Servicio de participaciones

    @Autowired
    private InscripcionService inscripcionService;

    @Autowired
    private UserService userService;


    // Obtener todos los eventos
    @GetMapping
    public List<Evento> listarEventos() {
        return eventoService.findAll();
    }

    // Obtener un evento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Evento> obtenerEvento(@PathVariable Long id) {
        Evento evento = eventoService.findById(id);
        if (evento != null) {
            return new ResponseEntity<>(evento, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<Evento> crearEvento(@RequestBody Evento evento, @RequestParam Long creadorId) {
        // Buscar al usuario creador
        User creador = userService.findById(creadorId);

        if (creador != null) {
            // Guardar el evento
            Evento eventoCreado = eventoService.save(evento);

            // Crear la participación del usuario en el evento
            ParticipacionEvento participacion = new ParticipacionEvento();
            participacion.setUsuario(creador);
            participacion.setEvento(eventoCreado);

            // Guardar la participación en la base de datos
            participacionService.save(participacion);

            return new ResponseEntity<>(eventoCreado, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    // Metodo para actualizar un evento existente
    @PutMapping("/{id}")
    public ResponseEntity<Evento> actualizarEvento(@PathVariable Long id, @RequestBody Evento eventoActualizado) {
        Evento evento = eventoService.findById(id);
        if (evento != null) {
            evento.setTitulo(eventoActualizado.getTitulo());
            evento.setDescripcion(eventoActualizado.getDescripcion());
            evento.setFechaEvento(eventoActualizado.getFechaEvento());
            evento.setHoraEvento(eventoActualizado.getHoraEvento());
            evento.setUbicacion(eventoActualizado.getUbicacion());
            evento.setCapacidad(eventoActualizado.getCapacidad());
            evento.setCategoria(eventoActualizado.getCategoria());
            evento.setDuracionEstimada(eventoActualizado.getDuracionEstimada());

            Evento eventoGuardado = eventoService.save(evento); // Guardamos el evento actualizado usando save
            return new ResponseEntity<>(eventoGuardado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un evento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEvento(@PathVariable Long id) {
        if (!eventoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Eliminar las participaciones asociadas
        List<ParticipacionEvento> participaciones = participacionService.findByEventoId(id);
        for (ParticipacionEvento participacion : participaciones) {
            participacionService.delete(participacion.getId());
        }

        // Eliminar las inscripciones asociadas
        List<Inscripcion> inscripciones = inscripcionService.findByEventoId(id);
        for (Inscripcion inscripcion : inscripciones) {
            inscripcionService.delete(inscripcion.getId());
        }

        // Eliminar el evento
        eventoService.delete(id);
        return ResponseEntity.noContent().build();
    }






}
