package com.hampcode.api;

import com.hampcode.model.entity.DietType;
import com.hampcode.service.DietTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/diet_type")
public class DietTypeController {
    private final DietTypeService dietTypeService;

    @GetMapping
    public ResponseEntity<List<DietType>> findAll() {
        List<DietType> dt = dietTypeService.findAll();
        return new ResponseEntity<>(dt, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DietType> get(@PathVariable Integer id) {
        DietType d = dietTypeService.getOne(id);
        return new ResponseEntity<>(d, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DietType> create(@RequestBody DietType dt){
        DietType d = dietTypeService.create(dt);
        return new ResponseEntity<>(d, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DietType> update(@PathVariable Integer id, @RequestBody DietType dt){
        DietType d = dietTypeService.update(id, dt);
        return new ResponseEntity<>(d, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DietType> delete(@PathVariable Integer id){
        dietTypeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}