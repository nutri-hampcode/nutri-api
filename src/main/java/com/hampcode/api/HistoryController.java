package com.hampcode.api;

import com.hampcode.model.entity.History;
import com.hampcode.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/histories")
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping
    public ResponseEntity<List<History>> list() {
        List<History> histories = historyService.findAll();
        return new ResponseEntity<>(histories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<History> get(@PathVariable Integer id) {
        History history = historyService.getOne(id);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<History> create(@RequestBody History history) {
        History a = historyService.create(history);
        return new ResponseEntity<>(a, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<History> delete(@PathVariable Integer id){
        historyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}