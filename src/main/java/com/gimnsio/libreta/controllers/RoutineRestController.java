package com.gimnsio.libreta.controllers;

import com.gimnsio.libreta.DTO.routines.RoutineEditDTO;
import com.gimnsio.libreta.DTO.routines.RoutineNewDTO;
import com.gimnsio.libreta.services.RoutineService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/routines")
@Tag(name = "Rutinas", description = "CRUD de Rutinas")
public class RoutineRestController {

    private final RoutineService routineService;

    public RoutineRestController(RoutineService routineService) {
        this.routineService = routineService;
    }

    @GetMapping
    public ResponseEntity<?> getAllRoutines(
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(this.routineService.getAllRoutines(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoutineById(@PathVariable Long id) {
        return ResponseEntity.ok(this.routineService.getRoutineById(id));
    }

    @PostMapping
    public ResponseEntity<?> createRoutine(@RequestBody RoutineNewDTO routineNewDTO) {
        return ResponseEntity.ok(this.routineService.createRoutine(routineNewDTO));
    }

    @PutMapping
    public ResponseEntity<?> updateRoutine(@RequestBody RoutineEditDTO routineEditDTO) {
        return ResponseEntity.ok(this.routineService.updateRoutine(routineEditDTO));// Mejorable
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoutine(@PathVariable Long id) {
        this.routineService.deleteRoutine(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getRoutineByUser(@PathVariable Long id,
                                              @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(this.routineService.getRoutinesByUser(id,pageable));
    }



}
