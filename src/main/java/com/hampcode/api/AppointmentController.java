package com.hampcode.api;

import com.hampcode.dto.AppointmentCreateUpdateDTO;
import com.hampcode.dto.AppointmentDetailsDTO;
import com.hampcode.dto.HistoryDTO;
import com.hampcode.service.AppointmentService;
import jakarta.validation.Valid;
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

    // Obtener todas las citas
    @GetMapping
    public ResponseEntity<List<AppointmentDetailsDTO>> list() {
        List<AppointmentDetailsDTO> appointments = appointmentService.findAll();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    // Obtener una cita por ID
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDetailsDTO> findById(@PathVariable Integer id) {
        AppointmentDetailsDTO appointment = appointmentService.findById(id);
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }

    // Crear una nueva cita
    @PostMapping("/{userId}")
    public ResponseEntity<AppointmentDetailsDTO> create(@PathVariable Integer userId, @Valid @RequestBody AppointmentCreateUpdateDTO appointmentDTO) {
        AppointmentDetailsDTO newAppointment = appointmentService.create(userId, appointmentDTO);
        return new ResponseEntity<>(newAppointment, HttpStatus.CREATED);
    }

    // Actualizar una cita existente por ID
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDetailsDTO> update(
            @PathVariable Integer id,
            @Valid @RequestBody AppointmentCreateUpdateDTO appointmentDTO) {
        AppointmentDetailsDTO updatedAppointment = appointmentService.update(id, appointmentDTO);
        return new ResponseEntity<>(updatedAppointment, HttpStatus.OK);
    }

    // Eliminar una cita por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        appointmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Obtener el lista de appointments en un history por usuario
    @GetMapping("/history/{userId}")
    public ResponseEntity<List<HistoryDTO>> findHistoryByUserId(@PathVariable Integer userId) {
        List<HistoryDTO> history = appointmentService.findHistoryByUserId(userId);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }

}