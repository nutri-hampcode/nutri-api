package com.hampcode.api;

import com.hampcode.model.entity.Goal;
import com.hampcode.service.GoalService;
import com.hampcode.dto.GoalRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/goals")
public class GoalController {
    private final GoalService goalService;

    // Listar los tres objetivos predefinidos
    @GetMapping
    public ResponseEntity<List<String>> list() {
        List<String> predefinedGoals = Arrays.asList("Bajar de peso", "Subir de peso", "Mantener peso");
        return new ResponseEntity<>(predefinedGoals, HttpStatus.OK);
    }

    // Obtener la selección actual del objetivo de un usuario
    @GetMapping("/{id}")
    public ResponseEntity<Goal> get(@PathVariable Integer id) {
        Goal goal = goalService.getOne(id);
        return new ResponseEntity<>(goal, HttpStatus.OK);
    }

    // Actualizar la selección del objetivo del usuario
    @PutMapping("/{id}")
    public ResponseEntity<Goal> update(@PathVariable Integer id, @RequestBody Goal goal) {
        Goal updatedGoal = goalService.update(id, goal);
        return new ResponseEntity<>(updatedGoal, HttpStatus.OK);
    }
}
