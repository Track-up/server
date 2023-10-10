package com.gimnsio.libreta.controllers;

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

    @PostMapping("/create/{routine_id}/{user_id}")
    public ResponseEntity<?> startTraining(@PathVariable long routine_id, @PathVariable long user_id){
        return ResponseEntity.ok(this.workoutService.startTraining(routine_id,user_id));
    }

}
