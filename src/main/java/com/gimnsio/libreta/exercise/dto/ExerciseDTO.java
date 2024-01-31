package com.gimnsio.libreta.exercise.dto;

import com.gimnsio.libreta.DTO.bodyPart.BodyPartForExerciseDTO;
import com.gimnsio.libreta.DTO.equipment.EquipmentForExerciseDTO;
import com.gimnsio.libreta.DTO.muscles.MusclePercentDTO;
import com.gimnsio.libreta.persistence.enums.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ExerciseDTO {

    private String id;
    private String name;
    private Force force;
    private Level level;
    private Mechanic mechanic;
    private Equipment equipment;
    private List<Muscle> primaryMuscles;
    private List<Muscle> secondaryMuscles;
    private List<String> instructions;
    private Category category;
    private List<String> images;

}
