package com.hampcode.api;

import com.hampcode.model.entity.Appointment;
import com.hampcode.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<List<Appointment>> list() {
        List<Appointment> appointments = appointmentService.findAll();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> get(@PathVariable Integer id) {
        Appointment appointment = appointmentService.getOne(id);
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Appointment> create(@RequestBody Appointment appointment) {
        Appointment a = appointmentService.create(appointment);
        return new ResponseEntity<>(a, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> update(@PathVariable Integer id, @RequestBody Appointment appointment){
        Appointment a = appointmentService.update(id, appointment);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Appointment> delete(@PathVariable Integer id){
        appointmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}