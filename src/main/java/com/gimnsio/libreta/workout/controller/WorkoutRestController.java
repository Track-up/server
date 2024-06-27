package com.gimnsio.libreta.workout.controller;

import com.gimnsio.libreta.workout.dto.WorkoutDTO;
import com.gimnsio.libreta.workout.service.WorkoutService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workouts")
@Tag(name = "Entrenamientos", description = "CRUD de Entrenamientos")
public class WorkoutRestController {

    @Autowired
    private WorkoutService workoutService;

    @GetMapping
    public ResponseEntity<?> getAllWorkouts(
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(this.workoutService.getAllWorkouts(pageable));
    }

    @GetMapping("/last/{userId}")
    public ResponseEntity<?> getLastWorkouts(
            @PathVariable Long userId,
            @RequestParam() Integer until) {
        return ResponseEntity.ok(this.workoutService.getLastWorkouts(userId, until));
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getWorkoutById(@PathVariable Long id) {
        return ResponseEntity.ok(this.workoutService.getWorkoutById(id));
    }

    @GetMapping("/{id}/stats")
    public ResponseEntity<?> getWorkoutStats(@PathVariable Long id) {
        return ResponseEntity.ok(this.workoutService.getWorkoutStats(id));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createWorkout(
            @RequestParam(value = "routine_id", required = false)  Long routineId,
            @RequestParam(value = "workout_id", required = false) Long workoutId,
            @RequestParam("user_id") Long userId,
            @RequestParam("difficulty") Long difficulty) {
        WorkoutDTO workout = null;
        if (workoutId != null && workoutId != 0) {
            workout = this.workoutService.createWorkoutByWorkout(workoutId, userId, difficulty);
        } else {
            workout = this.workoutService.createWorkoutByRoutine(routineId, userId, difficulty);
        }
        return ResponseEntity.ok(workout);
    }



    @PutMapping("/start")
    public ResponseEntity<?> startWorkout(
            @RequestBody @Valid WorkoutDTO workoutDTO) {
        return ResponseEntity.ok(this.workoutService.startWorkout(workoutDTO));
    }

    @PutMapping("/end")
    public ResponseEntity<?> endWorkout(
            @RequestBody @Valid WorkoutDTO workoutDTO) {
        return ResponseEntity.ok(this.workoutService.endWorkout(workoutDTO));
    }



}
