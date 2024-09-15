package com.hampcode.api;

import com.hampcode.model.entity.Availability;
import com.hampcode.model.entity.Doctor;
import com.hampcode.service.AvailabilityService;
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
    public ResponseEntity<List<Availability>> findAll() {
        List<Availability> availability = availabilityService.findAll();
        return new ResponseEntity<>(availability, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Availability> findById(@PathVariable Integer id) {
        Availability availability = availabilityService.findById(id);
        return new ResponseEntity<>(availability, HttpStatus.OK);
    }

    @GetMapping("/doctor/{id_doctor}")
    public ResponseEntity<List<Availability>> findAllByDoctor(@PathVariable Integer id_doctor) {
        List<Availability> availability = availabilityService.findDoctorAvailability(id_doctor);
        List<Availability> availables = availability.stream()
                .filter(avail -> !avail.getReserved())
                .collect(Collectors.toList());
        return new ResponseEntity<>(availables, HttpStatus.OK);
    }

    @PostMapping("/{id_doctor}")
    public ResponseEntity<Availability> create(@PathVariable Integer id_doctor,@RequestBody Availability availability) {
        Availability aux = availabilityService.create(id_doctor, availability);
        return new ResponseEntity<>(aux, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Availability> update(@PathVariable Integer id, @RequestBody Availability availability) {
        Availability aux = availabilityService.update(id, availability);
        return new ResponseEntity<>(aux, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Availability> delete(@PathVariable Integer id) {
        availabilityService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}