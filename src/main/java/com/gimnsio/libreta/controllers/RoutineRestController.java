package com.gimnsio.libreta.controllers;

import com.gimnsio.libreta.DTO.routines.RoutineEditDTO;
import com.gimnsio.libreta.DTO.routines.RoutineNewDTO;
import com.gimnsio.libreta.services.RoutineService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/routines")
@Tag(name = "Rutinas", description = "CRUD de Rutinas")
public class RoutineRestController {

    private final RoutineService routineService;

    public RoutineRestController(RoutineService routineService) {
        this.routineService = routineService;
    }

    @GetMapping
    public ResponseEntity<?> getRoutines(
            @RequestParam(name = "name", required = false) String name,
            @PageableDefault(size = 20) Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending());
        if (name!= null && !name.isEmpty()){
            return ResponseEntity.ok(this.routineService.getRoutinesByName(name,pageable));
        }else {
            return ResponseEntity.ok(this.routineService.getAllRoutines(pageable));
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoutineById(@PathVariable Long id) {
        return ResponseEntity.ok(this.routineService.getRoutineById(id));
    }

    @PostMapping
    public ResponseEntity<?> createRoutine(@RequestBody @Valid RoutineNewDTO routineNewDTO) {
        Map<String, Object> httpResponse = new HashMap<>();
        if (routineNewDTO.getExercises().isEmpty()){
            httpResponse.put("message", "La rutina no tiene ejercicios :(");
            return ResponseEntity.badRequest().body(httpResponse);
        }

        try {
            httpResponse.put("id", this.routineService.createRoutine(routineNewDTO).getId());
            httpResponse.put("message", "Rutina creada con exito :)");
            return ResponseEntity.ok(httpResponse);
        }catch (Exception e){
            httpResponse.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(httpResponse);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRoutine(@PathVariable Long id,@RequestBody RoutineEditDTO routineEditDTO) {
        return ResponseEntity.ok(this.routineService.updateRoutine(id,routineEditDTO));// Mejorable
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

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getRoutineByUsername(@PathVariable String username,
                                              @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(this.routineService.getRoutinesByUsername(username,pageable));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getRoutineByName(@PathVariable String name,
                                              @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(this.routineService.getRoutinesByName(name,pageable));
    }

    @PostMapping("/workout/{id}")
    public ResponseEntity<?> getRoutineByWorkout(@PathVariable Long id) {
        return ResponseEntity.ok(this.routineService.getRoutineByWorkout(id));
    }




}
