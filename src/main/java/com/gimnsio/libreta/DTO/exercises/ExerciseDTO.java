package com.gimnsio.libreta.DTO.exercises;

import com.gimnsio.libreta.DTO.bodyPart.BodyPartForExerciseDTO;
import com.gimnsio.libreta.DTO.equipment.EquipmentForExerciseDTO;
import com.gimnsio.libreta.DTO.muscles.MuscleForExerciseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ExerciseDTO {

    private Long id;
    private String name;
    private String gifUrl;
    private BodyPartForExerciseDTO bodyPart;
    private MuscleForExerciseDTO target;
    private EquipmentForExerciseDTO equipment;

}
