package com.hampcode.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hampcode.model.entity.Goal;
import com.hampcode.service.GoalService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/goals")
public class GoalController {
    private final GoalService goalService;

    @GetMapping
    public ResponseEntity<List<Goal>> list() {
        List<Goal> goals = goalService.findAll();
        return new ResponseEntity<>(goals, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Goal> get(@PathVariable Integer id) {
        Goal goal = goalService.getOne(id);
        return new ResponseEntity<>(goal, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Goal> create(@RequestBody Goal goal) {
        Goal g = goalService.create(goal);
        return new ResponseEntity<>(g, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Goal> update(@PathVariable Integer id, @RequestBody Goal goal){
        Goal g = goalService.update(id, goal);
        return new ResponseEntity<>(g, HttpStatus.OK);
    }

}