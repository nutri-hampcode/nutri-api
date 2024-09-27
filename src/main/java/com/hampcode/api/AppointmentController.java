package com.hampcode.api;

import com.hampcode.service.AppointmentService;
import com.hampcode.dto.AppointmentDTO;
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
    public ResponseEntity<List<AppointmentDTO>> list() {
        List<AppointmentDTO> appointments = appointmentService.findAll();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> get(@PathVariable Integer id) {
        AppointmentDTO appointment = appointmentService.getOne(id);
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<AppointmentDTO> create(@PathVariable Integer id, @RequestBody AppointmentDTO appointment) {
        AppointmentDTO a = appointmentService.create(id,appointment);
        return new ResponseEntity<>(a, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDTO> update(@PathVariable Integer id, @RequestBody AppointmentDTO appointment){
        AppointmentDTO a = appointmentService.update(id, appointment);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        appointmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
