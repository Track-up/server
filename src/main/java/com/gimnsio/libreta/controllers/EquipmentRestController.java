package com.gimnsio.libreta.controllers;

import com.gimnsio.libreta.persistence.entities.EquipmentEntity;
import com.gimnsio.libreta.services.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/equipment")
public class EquipmentRestController {
    @Autowired
    private EquipmentService equipmentService;

    @PostMapping("/insert")
    public ResponseEntity<?> createMuscles(@RequestBody Set<EquipmentEntity> equipments) {
        ;
        return ResponseEntity.ok(equipmentService.createEquipments(equipments));
    }
}
