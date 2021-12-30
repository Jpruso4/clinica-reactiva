package com.springBajo8.springBajo8.service.impl;

//import com.yoandypv.reactivestack.messages.domain.Message;
//import com.yoandypv.reactivestack.messages.repository.MessageRepository;
//import com.yoandypv.reactivestack.messages.service.MessageService;
import com.springBajo8.springBajo8.domain.citasDTOReactiva;
import com.springBajo8.springBajo8.repository.IcitasReactivaRepository;
import com.springBajo8.springBajo8.service.IcitasReactivaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class citasReactivaServiceImpl implements IcitasReactivaService {

    private final IcitasReactivaRepository IcitasReactivaRepository;

    @Autowired
    public citasReactivaServiceImpl (IcitasReactivaRepository iCitasReactivaRepository){
        this.IcitasReactivaRepository = iCitasReactivaRepository;
    }

    @Override
    public Mono<citasDTOReactiva> save(citasDTOReactiva citasDTOReactiva) {
        return this.IcitasReactivaRepository.save(citasDTOReactiva);
    }

    @Override
    public Mono<citasDTOReactiva> delete(String id) {
        return this.IcitasReactivaRepository
                .findById(id)
                .flatMap(p -> this.IcitasReactivaRepository.deleteById(p.getId()).thenReturn(p));

    }

    @Override
    public Mono<citasDTOReactiva> update(String id, citasDTOReactiva citasDTOReactiva) {
        return this.IcitasReactivaRepository.findById(id)
                .flatMap(citasDTOReactiva1 -> {
                    citasDTOReactiva.setId(id);
                    return save(citasDTOReactiva);
                })
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Flux<citasDTOReactiva> findByIdPaciente(String idPaciente) {
        return this.IcitasReactivaRepository.findByIdPaciente(idPaciente);
    }

    @Override
    public Flux<citasDTOReactiva> findAll() {
        return this.IcitasReactivaRepository.findAll();
    }

    @Override
    public Mono<citasDTOReactiva> findById(String id) {
        return this.IcitasReactivaRepository.findById(id);
    }

    @Override
    public Mono<ResponseEntity<citasDTOReactiva>> cancelAppointment(String id) {
        return findById(id).flatMap(citasDTOReactiva -> {
                    citasDTOReactiva.setEstadoReservaCita("Cancel");
                    citasDTOReactiva.setEstadoCita(false);
                    return save(citasDTOReactiva);
                }).flatMap(citasDTOReactiva -> Mono.just(ResponseEntity.ok(citasDTOReactiva)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @Override
    public Mono<citasDTOReactiva> findByFechaReservaCitaAndHoraReservaCita(LocalDate fechaReservaCita, String horaReservaCita) {
        return this.IcitasReactivaRepository.findByFechaReservaCitaAndHoraReservaCita(fechaReservaCita, horaReservaCita);
    }

    @Override
    public Mono<citasDTOReactiva> getDoctorName(String id) {
        Mono<citasDTOReactiva> doctorData = findById(id).flatMap(citasDTOReactiva -> {
            citasDTOReactiva doctor = new citasDTOReactiva();
            doctor.setNombreMedico(citasDTOReactiva.getNombreMedico());
            doctor.setApellidosMedico(citasDTOReactiva.getApellidosMedico());
            return Mono.just(doctor);
        });
        return doctorData;
    }

    @Override
    public Flux<citasDTOReactiva> getPadecimientos(String idPaciente) {
        Flux<citasDTOReactiva> patientData = findByIdPaciente(idPaciente).flatMap(citasDTOReactiva -> {
            citasDTOReactiva patient = new citasDTOReactiva();
            patient.setNombrePaciente(citasDTOReactiva.getNombrePaciente());
            patient.setPadecimientos(citasDTOReactiva.getPadecimientos());
            return Mono.just(patient);
        });
        return patientData;
    }
}
