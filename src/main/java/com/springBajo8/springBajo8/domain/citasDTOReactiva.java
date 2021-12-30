package com.springBajo8.springBajo8.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "citas")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class citasDTOReactiva {

    @Id
    private String id = UUID.randomUUID().toString().substring(0, 10);
    private String idPaciente;
    private String nombrePaciente;
    private String apellidosPaciente;
    private String nombreMedico;
    private String apellidosMedico;
    private LocalDate fechaReservaCita;
    private String horaReservaCita;
    private String estadoReservaCita;
    private Boolean estadoCita;
    private String padecimientos;
    private String tratamientos;

}
