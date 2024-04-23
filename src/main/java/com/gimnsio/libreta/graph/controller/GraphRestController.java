package com.gimnsio.libreta.graph.controller;

import com.gimnsio.libreta.graph.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/track")
public class GraphRestController {

    @Autowired
    GraphService graphService;

    @GetMapping("/exercise-by-user")
    public ResponseEntity<?> getGraphOfExercise(@RequestParam Long userId, @RequestParam String exerciseId, @RequestParam String until){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = dateFormat.parse(until);
            return ResponseEntity.ok(graphService.getGraphOfExercise(userId, date, exerciseId));
        } catch (ParseException e) {
            System.out.println("Error al convertir la fecha: " + e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

}
