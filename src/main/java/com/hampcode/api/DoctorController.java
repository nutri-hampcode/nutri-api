package com.hampcode.api;

import com.hampcode.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hampcode.model.entity.Doctor;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    @GetMapping
    public ResponseEntity<List<Doctor>> list(){
        List<Doctor> doctors = doctorService.findAll();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> get(@PathVariable Integer id){
        Doctor d = doctorService.getOne(id);
        return new ResponseEntity<>(d, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Doctor> create(@RequestBody Doctor doctor){
        Doctor d = doctorService.create(doctor);
        return new ResponseEntity<>(d, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> update(@PathVariable Integer id, @RequestBody Doctor doctor){
        Doctor d = doctorService.update(id, doctor);
        return new ResponseEntity<>(d, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Doctor> delete(@PathVariable Integer id){
        doctorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
