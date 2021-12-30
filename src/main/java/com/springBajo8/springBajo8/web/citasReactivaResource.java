package com.springBajo8.springBajo8.web;

import com.springBajo8.springBajo8.domain.citasDTOReactiva;
import com.springBajo8.springBajo8.service.IcitasReactivaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
public class citasReactivaResource {

    @Autowired
    private IcitasReactivaService icitasReactivaService;

    @PostMapping("/citasReactivas")
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<citasDTOReactiva> save(@RequestBody citasDTOReactiva citasDTOReactiva) {
        return this.icitasReactivaService.save(citasDTOReactiva);
    }

    @DeleteMapping("/citasReactivas/{id}")
    private Mono<ResponseEntity<citasDTOReactiva>> delete(@PathVariable("id") String id) {
        return this.icitasReactivaService.delete(id)
                .flatMap(citasDTOReactiva -> Mono.just(ResponseEntity.ok(citasDTOReactiva)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }

    @PutMapping("/citasReactivas/{id}")
    private Mono<ResponseEntity<citasDTOReactiva>> update(@PathVariable("id") String id, @RequestBody citasDTOReactiva citasDTOReactiva) {
        return this.icitasReactivaService.update(id, citasDTOReactiva)
                .flatMap(citasDTOReactiva1 -> Mono.just(ResponseEntity.ok(citasDTOReactiva1)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }

    @GetMapping("/citasReactivas/{idPaciente}/byidPaciente")
    private Flux<citasDTOReactiva> findAllByidPaciente(@PathVariable("idPaciente") String idPaciente) {
        return this.icitasReactivaService.findByIdPaciente(idPaciente);
    }

    @GetMapping(value = "/citasReactivas")
    private Flux<citasDTOReactiva> findAll() {
        return this.icitasReactivaService.findAll();
    }

    @PutMapping("/citasReactivas/cancelAppointment/{id}")
    private Mono<ResponseEntity<citasDTOReactiva>> cancelAppointment(@PathVariable("id") String id) {
        return this.icitasReactivaService.cancelAppointment(id);

    }

    @GetMapping("/citasReactivas/{date}/{hour}")
    private Mono<citasDTOReactiva> findByDate(@PathVariable(value = "date") String date, @PathVariable(value = "hour") String hour) {
        return this.icitasReactivaService.findByDate(LocalDate.parse(date), hour);
    }

    @GetMapping("/citasReactivas/doctorName/{id}")
    private Mono<citasDTOReactiva> getDoctorName(@PathVariable("id") String id) {
        return this.icitasReactivaService.getDoctorName(id);

    }

    @GetMapping("/citasReactivas/nombreMedico/{id}")
    private Mono<citasDTOReactiva> getPadecimientos(@PathVariable("id") String id){
        return this.icitasReactivaService.getPadecimientos(id);
    }

}
