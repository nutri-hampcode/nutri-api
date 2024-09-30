package com.hampcode.api;

import com.hampcode.dto.DietTypeDTO;
import com.hampcode.service.DietTypeService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<DietTypeDTO>> findAll() {
        List<DietTypeDTO> dt = dietTypeService.findAll();
        return new ResponseEntity<>(dt, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DietTypeDTO> get(@PathVariable Integer id) {
        DietTypeDTO d = dietTypeService.getOne(id);
        return new ResponseEntity<>(d, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DietTypeDTO> create(@Valid @RequestBody DietTypeDTO dt){
        DietTypeDTO d = dietTypeService.create(dt);
        return new ResponseEntity<>(d, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DietTypeDTO> update(@PathVariable Integer id, @Valid @RequestBody DietTypeDTO dt){
        DietTypeDTO d = dietTypeService.update(id, dt);
        return new ResponseEntity<>(d, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        dietTypeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}