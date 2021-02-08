package com.ooredoo.franchise.api;

import com.ooredoo.franchise.entity.Franchise;
import com.ooredoo.franchise.service.FranchiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/")
public class FranchiseController {

    @Autowired
    FranchiseService franchiseService;

    @GetMapping("getAll")
    public ResponseEntity<List<Franchise>> getAll() {
        return ResponseEntity.ok(franchiseService.getAll());
    }

    @PostMapping("franchise/filter")
    public ResponseEntity<List<Franchise>> getALlWithFilter(@RequestBody Franchise franchise) {
        return ResponseEntity.ok(franchiseService.getAllWithFilter(franchise));
    }

    @PostMapping("save")
    public ResponseEntity<Franchise> save(@Valid @RequestBody Franchise franchise) {
        return ResponseEntity.ok(franchiseService.save(franchise));
    }

    @PutMapping("update")
    public ResponseEntity<Franchise> update(@Valid @RequestBody Franchise franchise) {
        return ResponseEntity.ok(franchiseService.update(franchise));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!franchiseService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
