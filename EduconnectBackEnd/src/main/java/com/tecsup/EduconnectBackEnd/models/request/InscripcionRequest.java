package com.tecsup.EduconnectBackEnd.models.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InscripcionRequest {
    private Long eventoId;
    private Long usuarioId;
    private Boolean estadoInscripcion; // Si es necesario
}