package com.springBajo8.springBajo8.repository;

import com.springBajo8.springBajo8.domain.citasDTOReactiva;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface IcitasReactivaRepository extends ReactiveMongoRepository<citasDTOReactiva, String> {
    Flux <citasDTOReactiva> findByIdPaciente(String idPaciente);
    Mono<citasDTOReactiva> findByFechaReservaCitaAndHoraReservaCita(LocalDate fechaReservaCita, String horaReservaCita);
}
