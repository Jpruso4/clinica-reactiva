package com.springBajo8.springBajo8.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "citas")
@Data
public class citasDTOReactiva {

    @Id
    private String id = UUID.randomUUID().toString().substring(0, 10);
    private String idPaciente;
    private String nombrePaciente;
    private String apellidosPaciente;
    private String nombreMedico;
    private String apellidosMedico;
    private String fechaReservaCita;
    private String horaReservaCita;
    private String estadoReservaCita;
    private Boolean estadoCita;
    private String padecimientos;
    private String tratamientos;

}
