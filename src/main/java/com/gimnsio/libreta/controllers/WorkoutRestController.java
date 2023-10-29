package com.gimnsio.libreta.controllers;

import com.gimnsio.libreta.DTO.workout.WorkoutDTO;
import com.gimnsio.libreta.services.WorkoutService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workout")
@Tag(name = "Entrenamientos", description = "CRUD de Entrenamientos")
public class WorkoutRestController {

    @Autowired
    private WorkoutService workoutService;

    @GetMapping
    public ResponseEntity<?> getAllWorkouts(
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(this.workoutService.getAllWorkouts(pageable));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createWorkout(
            @RequestParam("routine_id") Long routineId,
            @RequestParam("user_id") Long userId,
            @RequestParam("difficulty") Long difficulty){
        return ResponseEntity.ok(this.workoutService.createWorkout(routineId,userId,difficulty ));
    }

    @PutMapping("/start")
    public ResponseEntity<?> startWorkout(
            @RequestBody WorkoutDTO workoutDTO){
        this.workoutService.startWorkout(workoutDTO);
        return ResponseEntity.ok("started");
    }

    @PutMapping("/end")
    public ResponseEntity<?> endWorkout(
            @RequestBody WorkoutDTO workoutDTO){
        this.workoutService.endWorkout(workoutDTO);
        return ResponseEntity.ok("Ended");
    }

}
