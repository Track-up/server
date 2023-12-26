package com.gimnsio.libreta.controllers;

import com.gimnsio.libreta.DTO.exercises.ExerciseNewDTO;
import com.gimnsio.libreta.persistence.entities.ExerciseEntity;
import com.gimnsio.libreta.persistence.specification.ExerciseSpecification;
import com.gimnsio.libreta.services.ExerciseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Set;

@RestController
@RequestMapping("/exercises")
@Tag(name = "Ejercicios", description = "CRUD de Ejercicios")
public class ExerciseRestController {

    private ExerciseService exerciseService;

    public ExerciseRestController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public ResponseEntity<?> getExercises(
            @PageableDefault(size = 20) Pageable pageable,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "muscle", required = false) String muscle,
            @RequestParam(name = "force", required = false) String force,
            @RequestParam(name = "level", required = false) String level,
            @RequestParam(name = "mechanic", required = false) String mechanic,
            @RequestParam(name = "equipment", required = false) String equipment,
            @RequestParam(name = "category", required = false) String category,
            @RequestParam(defaultValue = "en") String locale) {


        Locale userLocale = new Locale(locale);
        String nameEs = null;
        if (locale.equals("es")) {//falta poner ejercicios en la base de datos :)
            nameEs = name;
            name = null;
        }
        return ResponseEntity.ok(exerciseService.getExercises(new ExerciseSpecification(name, nameEs, muscle, force, level, mechanic, equipment, category), pageable, userLocale));
//        if (name != null && !name.isEmpty()) {
//            return ResponseEntity.ok(this.exerciseService.getExercisesByName(name, pageable, userLocale));
//        } else if (muscle != null && !muscle.isEmpty()) {
//            return ResponseEntity.ok(this.exerciseService.getExercisesByMuscle(muscle,pageable, userLocale));
//        } else if (force != null && !force.isEmpty()) {
//            return ResponseEntity.ok(this.exerciseService.getExercisesByForce(force, pageable, userLocale));
//        } else if (level != null && !level.isEmpty()) {
//            return ResponseEntity.ok(this.exerciseService.getExercisesByLevel(level, pageable, userLocale));
//        } else if (mechanic != null && !mechanic.isEmpty()) {
//            return ResponseEntity.ok(this.exerciseService.getExercisesByMechanic(mechanic, pageable, userLocale));
//        } else if (equipment != null && !equipment.isEmpty()) {
//            return ResponseEntity.ok(this.exerciseService.getExercisesByEquipment(equipment, pageable, userLocale));
//        } else if (category != null && !category.isEmpty()) {
//            return ResponseEntity.ok(this.exerciseService.getExercisesByCategory(category, pageable, userLocale));
//        }
//        return ResponseEntity.ok(this.exerciseService.getAllExercises(pageable, userLocale));

    }

    @GetMapping("{id}")
    public ResponseEntity<?> getExerciseById(@PathVariable String id) {
        return ResponseEntity.ok(exerciseService.getExerciseById(id));
    }

//    @GetMapping("/body_part/{id}")
//    public ResponseEntity<?> getExercisesByBodyPart(@PathVariable Long id) {
//        return ResponseEntity.ok(exerciseService.getExercisesByBodyPart(id));
//    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateExercise(@PathVariable String id, @RequestBody ExerciseEntity exercise) {
        return ResponseEntity.ok(exerciseService.updateExercise(id, exercise));
    }

    @PostMapping
    public ResponseEntity<?> createExercise(@RequestBody ExerciseNewDTO exercise) {
        return ResponseEntity.ok(exerciseService.createExercise(exercise));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/insert")
    public ResponseEntity<?> createExercises(@RequestBody Set<ExerciseEntity> exercises) {
        ;
        return ResponseEntity.ok(exerciseService.createExercises(exercises));
    }

}
