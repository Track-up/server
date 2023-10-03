package com.gimnsio.libreta.controllers;

import com.gimnsio.libreta.DTO.muscles.MuscleToImportDTO;
import com.gimnsio.libreta.domain.Muscle;
import com.gimnsio.libreta.persistence.entities.BodyPartEntity;
import com.gimnsio.libreta.services.MuscleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/muscles")
@Tag(name = "Musculos", description = "CRUD de musculos")
public class MuscleRestController {

    private final MuscleService muscleService;

    public MuscleRestController(MuscleService muscleService) {

        this.muscleService = muscleService;
    }

    @GetMapping
    public ResponseEntity<List<Muscle>> getMuscles(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(this.muscleService.getAllMuscles(pageable));
    }

    @PostMapping
    public Muscle createMuscle(@RequestBody Muscle muscle) {
        return muscleService.createMuscle(muscle);// muscleRepository.save(musculo);
    }
    @PostMapping("/insert")
    public ResponseEntity<?> createMuscles(@RequestBody Set<MuscleToImportDTO> muscles) {
        ;
        return ResponseEntity.ok(muscleService.createMuscles(muscles));// muscleRepository.save(musculo);
    }

    @PostMapping("/insert/bodyParts")
    public ResponseEntity<?> createBodyParts(@RequestBody Set<BodyPartEntity> bodyPart) {
        return ResponseEntity.ok(muscleService.createBodyParts(bodyPart));// muscleRepository.save(musculo);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Muscle> getMuscleById(@PathVariable Long id) {
        return ResponseEntity.ok(muscleService.getMuscleById(id));
        // Optional<Muscle> musculo = muscleRepository.findById(id);
        // if (musculo.isPresent()) {
        // return ResponseEntity.ok(musculo.get());
        // } else {
        // return ResponseEntity.notFound().build();
        // }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMuscle(@PathVariable Long id, @RequestBody Muscle muscle) {
        return ResponseEntity.ok(muscleService.updateMuscle(id, muscle));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMuscle(@PathVariable Long id) {
        muscleService.deleteMuscle(id);
        return ResponseEntity.noContent().build();
        // Optional<Muscle> musculo = muscleRepository.findById(id);
        // if (musculo.isPresent()) {
        // muscleRepository.delete(musculo.get());
        // return ResponseEntity.noContent().build();
        // } else {
        // return ResponseEntity.notFound().build();
        // }
    }
}
