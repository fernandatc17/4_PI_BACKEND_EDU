package com.tecsup.EduconnectBackEnd.models;

import jakarta.persistence.*;
        import lombok.*;

        import java.util.Date;

@Entity
@Table(name = "Auditoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entity_name", nullable = false)
    private String entityName;

    @Column(name = "operation", nullable = false)
    private String operation;

    @Column(name = "timestamp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "details", length = 500)
    private String details;
}
