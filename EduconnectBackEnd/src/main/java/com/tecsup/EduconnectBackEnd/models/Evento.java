package com.tecsup.EduconnectBackEnd.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Eventos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEventos")
    private Long id;

    @Column(name = "Titulo", nullable = false, length = 100)
    private String titulo;

    @Column(name = "Descripcion", length = 200)
    private String descripcion;

    @Column(name = "Fecha_publicacion")
    private Date fechaPublicacion;

    @Column(name = "Fecha_modificacion")
    private Date fechaModificacion;

    @Column(name = "Fecha_evento")
    private Date fechaEvento;

    @Column(name = "Hora_evento", length = 45)
    private String horaEvento;

    @Column(name = "Ubicacion", length = 100)
    private String ubicacion;

    @Column(name = "Capacidad")
    private Integer capacidad;

    @Column(name = "Categoria", length = 45)
    private String categoria;

    @Column(name = "Duracion_Estimada")
    private Integer duracionEstimada;

    // Relación con la tabla Usuarios (Muchos a Muchos)
    @ManyToMany(mappedBy = "eventos")
    private Set<User> usuarios;
}
