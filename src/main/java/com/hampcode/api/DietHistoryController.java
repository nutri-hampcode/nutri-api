package com.hampcode.api;

import com.hampcode.dto.DietHistoryCUDTO;
import com.hampcode.dto.DietHistoryDetailsDTO;
import com.hampcode.dto.MealCUDTO;
import com.hampcode.dto.MealDetailsDTO;
import com.hampcode.model.entity.DietHistory;
import com.hampcode.model.entity.Exercise;
import com.hampcode.service.DietHistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/diet_history")
public class DietHistoryController {
    private final DietHistoryService dietHistoryService;

    @GetMapping
    public ResponseEntity<List<DietHistoryDetailsDTO>> findAll() {
        List<DietHistoryDetailsDTO> meal = dietHistoryService.findAll();
        return new ResponseEntity<>(meal, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DietHistoryDetailsDTO> findById(@PathVariable Integer id) {
        DietHistoryDetailsDTO dh = dietHistoryService.findById(id);
        return new ResponseEntity<>(dh, HttpStatus.OK);
    }

    @GetMapping("/page/{id_user}")
    public ResponseEntity<Page<DietHistoryDetailsDTO>> paginateDH(
            @PathVariable Integer id_user,
            @PageableDefault(size = 5, sort = "id") Pageable pageable) {
        Page<DietHistoryDetailsDTO> dh = dietHistoryService.paginate(id_user, pageable);
        return new ResponseEntity<>(dh, HttpStatus.OK);
    }

    @PostMapping("/user/{id_user}/meal/{id_meal}")
    public ResponseEntity<DietHistoryDetailsDTO> create(@PathVariable Integer id_user,@PathVariable Integer id_meal, @Valid @RequestBody DietHistoryCUDTO dietCDTO) {
        DietHistoryDetailsDTO m = dietHistoryService.create(id_user,id_meal, dietCDTO);
        return new ResponseEntity<>(m, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/user/{id_user}")
    public ResponseEntity<DietHistoryDetailsDTO> update(@PathVariable Integer id,@PathVariable Integer id_user,@Valid @RequestBody DietHistoryCUDTO dietUpdateDTO) {
        DietHistoryDetailsDTO aux = dietHistoryService.update(id, id_user,null ,dietUpdateDTO);
        return new ResponseEntity<>(aux, HttpStatus.OK);
    }
    @PutMapping("/{id}/user/{id_user}/meal/{id_meal}")
    public ResponseEntity<DietHistoryDetailsDTO> update2(@PathVariable Integer id,@PathVariable Integer id_user,@PathVariable Integer id_meal,@Valid @RequestBody DietHistoryCUDTO dietUpdateDTO) {
        DietHistoryDetailsDTO aux = dietHistoryService.update(id,id_user, id_meal,dietUpdateDTO);
        return new ResponseEntity<>(aux, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        dietHistoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
