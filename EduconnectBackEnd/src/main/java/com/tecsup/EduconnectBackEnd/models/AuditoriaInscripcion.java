package com.tecsup.EduconnectBackEnd.models;


import jakarta.persistence.*;
        import lombok.*;

        import java.util.Date;

@Entity
@Table(name = "AuditoriaInscripcion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuditoriaInscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entity_name", nullable = false)
    private String entityName;  // Será "Inscripcion" para identificar la entidad afectada

    @Column(name = "operation", nullable = false)
    private String operation;  // "CREATE", "UPDATE", o "DELETE"

    @Column(name = "timestamp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;  // Fecha y hora de la operación

    @Column(name = "user_id")
    private Long userId;  // ID del usuario, si es necesario

    @Column(name = "details", length = 600)
    private String details;  // Información detallada de la operación realizada
}
