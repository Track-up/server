//package com.gimnsio.libreta.controllers;
//
//import com.gimnsio.libreta.domain.Exercise;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/import")
//public class ImportJSONRestController {
//
//
//    @PostMapping("/bodyParts")
//    public ResponseEntity<?> createExercise(@RequestBody Exercise exercise) {
//        return ResponseEntity.ok(1);
//    }
//
//    @PostMapping("/equipment")
//    public ResponseEntity<?> createExercise(@RequestBody Exercise exercise) {
//        return ResponseEntity.ok("");
//    }
//
//    @PostMapping("/exercises")
//    public ResponseEntity<?> createExercise(@RequestBody Exercise exercise) {
//        return ResponseEntity.ok(exerciseService.createExercise(exercise));
//    }
//
//    @PostMapping("/muscles")
//    public ResponseEntity<?> createExercise(@RequestBody Exercise exercise) {
//        return ResponseEntity.ok(exerciseService.createExercise(exercise));
//    }
//
//    @PostMapping("/routines")
//    public ResponseEntity<?> createExercise(@RequestBody Exercise exercise) {
//        return ResponseEntity.ok(exerciseService.createExercise(exercise));
//    }
//
//
//}