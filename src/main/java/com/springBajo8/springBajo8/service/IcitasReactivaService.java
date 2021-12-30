package com.springBajo8.springBajo8.service;

import com.springBajo8.springBajo8.domain.citasDTOReactiva;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface IcitasReactivaService {
    Mono<citasDTOReactiva> save(citasDTOReactiva citasDTOReactiva);

    Mono<citasDTOReactiva> delete(String id);

    Mono<citasDTOReactiva> update(String id, citasDTOReactiva citasDTOReactiva);

    Flux<citasDTOReactiva> findByIdPaciente(String idPaciente);

    Flux<citasDTOReactiva> findAll();

    Mono<citasDTOReactiva> findById(String id);

    Mono<ResponseEntity<citasDTOReactiva>> cancelAppointment(String id);

    Mono<citasDTOReactiva> findByFechaReservaCitaAndHoraReservaCita(LocalDate fechaReservaCita, String horaReservaCita);

    Mono<citasDTOReactiva> getDoctorName(String id);

    Flux<citasDTOReactiva> getPadecimientos(String idPaciente);
}
