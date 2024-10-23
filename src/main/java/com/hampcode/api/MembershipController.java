package com.hampcode.api;

import com.hampcode.model.entity.Membership;
import com.hampcode.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/memberships")
public class MembershipController {
    private final MembershipService membershipService;

    @GetMapping
    public ResponseEntity<List<Membership>> list() {
        List<Membership> memberships = membershipService.findAll();
        return new ResponseEntity<>(memberships, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Membership> get(@PathVariable Integer id) {
        Membership membership = membershipService.findById(id);
        return new ResponseEntity<>(membership, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Membership> create(@RequestBody Membership membership) {
        Membership m = membershipService.create(membership);
        return new ResponseEntity<>(m, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Membership> update(@PathVariable Integer id, @RequestBody Membership membership) {
        Membership m = membershipService.update(id, membership);
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        membershipService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}