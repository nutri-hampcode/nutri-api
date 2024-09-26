package com.hampcode.api;

import com.hampcode.dto.AvailabilityCreateUpdateDTO;
import com.hampcode.dto.AvailabilityDetailsDTO;
import com.hampcode.model.entity.Availability;
import com.hampcode.model.entity.Doctor;
import com.hampcode.service.AvailabilityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/availability")
public class AvailabilityController {
    private final AvailabilityService availabilityService;

    @GetMapping
    public ResponseEntity<List<AvailabilityDetailsDTO>> findAll() {
        List<AvailabilityDetailsDTO> availability = availabilityService.findAll();
        return new ResponseEntity<>(availability, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvailabilityDetailsDTO> findById(@PathVariable Integer id) {
        AvailabilityDetailsDTO availability = availabilityService.findById(id);
        return new ResponseEntity<>(availability, HttpStatus.OK);
    }

    @GetMapping("/doctor/{id_doctor}")
    public ResponseEntity<List<AvailabilityDetailsDTO>> findAllByDoctor(@PathVariable Integer id_doctor) {
        List<AvailabilityDetailsDTO> availability = availabilityService.findDoctorAvailability(id_doctor);
        List<AvailabilityDetailsDTO> availables = availability.stream()
                .filter(avail -> !avail.getReserved())
                .collect(Collectors.toList());
        return new ResponseEntity<>(availables, HttpStatus.OK);
    }

    @PostMapping("/{id_doctor}")
    public ResponseEntity<AvailabilityDetailsDTO> create(@PathVariable Integer id_doctor, @Valid @RequestBody AvailabilityCreateUpdateDTO availabilityCreateDTO) {
        AvailabilityDetailsDTO aux = availabilityService.create(id_doctor, availabilityCreateDTO);
        return new ResponseEntity<>(aux, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvailabilityDetailsDTO> update(@PathVariable Integer id,@Valid @RequestBody AvailabilityCreateUpdateDTO availabilityUpdateDTO) {
        AvailabilityDetailsDTO aux = availabilityService.update(id, availabilityUpdateDTO);
        return new ResponseEntity<>(aux, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Availability> delete(@PathVariable Integer id) {
        availabilityService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}