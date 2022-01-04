package com.springBajo8.springBajo8.web;

import com.springBajo8.springBajo8.domain.citasDTOReactiva;
import com.springBajo8.springBajo8.repository.IcitasReactivaRepository;
import com.springBajo8.springBajo8.service.IcitasReactivaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
public class citasReactivaResource {

    private final IcitasReactivaService icitasReactivaService;

    @Autowired
    public citasReactivaResource(IcitasReactivaService icitasReactivaService){
        this.icitasReactivaService = icitasReactivaService;
    }

    @PostMapping("/citasReactivas")
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    private Mono<citasDTOReactiva> save(@RequestBody citasDTOReactiva citasDTOReactiva) {
        return icitasReactivaService.save(citasDTOReactiva);
    }

    @DeleteMapping("/citasReactivas/{id}")
    @CrossOrigin
    private Mono<ResponseEntity<citasDTOReactiva>> delete(@PathVariable("id") String id) {
        return icitasReactivaService.delete(id)
                .flatMap(citasDTOReactiva -> Mono.just(ResponseEntity.ok(citasDTOReactiva)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @PutMapping("/citasReactivas/{id}")
    @CrossOrigin
    private Mono<ResponseEntity<citasDTOReactiva>> update(@PathVariable("id") String id, @RequestBody citasDTOReactiva citasDTOReactiva) {
        return icitasReactivaService.update(id, citasDTOReactiva)
                .flatMap(citasDTOReactiva1 -> Mono.just(ResponseEntity.ok(citasDTOReactiva1)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping("/citasReactivas/{idPaciente}/byidPaciente")
    @CrossOrigin
    private Flux<citasDTOReactiva> findAllByidPaciente(@PathVariable("idPaciente") String idPaciente) {
        return icitasReactivaService.findByIdPaciente(idPaciente);
    }

    @GetMapping(value = "/citasReactivas")
    @CrossOrigin
    private Flux<citasDTOReactiva> findAll() {
        return this.icitasReactivaService.findAll();
    }

    @PutMapping("/citasReactivas/cancelAppointment/{id}")
    @CrossOrigin
    private Mono<ResponseEntity<citasDTOReactiva>> cancelAppointment(@PathVariable("id") String id) {
        return icitasReactivaService.cancelAppointment(id);
    }

    @GetMapping("/citasReactivas/{fechaReservaCita}/{horaReservaCita}")
    @CrossOrigin
    private Mono<citasDTOReactiva> findByDateAndHour(@PathVariable(value = "fechaReservaCita") String fechaReservaCita, @PathVariable(value = "horaReservaCita") String horaReservaCita) {
        return icitasReactivaService.findByFechaReservaCitaAndHoraReservaCita(LocalDate.parse(fechaReservaCita), horaReservaCita);
    }

    @GetMapping("/citasReactivas/doctorName/{id}")
    @CrossOrigin
    private Mono<citasDTOReactiva> getDoctorName(@PathVariable("id") String id) {
        return icitasReactivaService.getDoctorName(id);
    }

    @GetMapping("/citasReactivas/padecimientos/{id}")
    @CrossOrigin
    private Flux<citasDTOReactiva> getPadecimientos(@PathVariable("id") String idPaciente){
        return icitasReactivaService.getPadecimientos(idPaciente);
    }

}
