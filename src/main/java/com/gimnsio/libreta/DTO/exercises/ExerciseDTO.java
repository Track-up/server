package com.gimnsio.libreta.DTO.exercises;

import com.gimnsio.libreta.DTO.bodyPart.BodyPartForExerciseDTO;
import com.gimnsio.libreta.DTO.equipment.EquipmentForExerciseDTO;
import com.gimnsio.libreta.DTO.muscles.MusclePercentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ExerciseDTO {

    private Long id;
    private String name;
    private String image;
    private List<BodyPartForExerciseDTO> bodyParts;
    private List<MusclePercentDTO> muscles;
    private EquipmentForExerciseDTO equipment;

}
