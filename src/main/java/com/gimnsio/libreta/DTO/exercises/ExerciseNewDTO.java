package com.gimnsio.libreta.DTO.exercises;

import com.gimnsio.libreta.DTO.bodyPart.BodyPartIdDTO;
import com.gimnsio.libreta.DTO.equipment.EquipmentIdDTO;
import com.gimnsio.libreta.DTO.muscles.MusclePercentDTO;
import com.gimnsio.libreta.DTO.muscles.MusclePercentIdDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ExerciseNewDTO {

    private String name;
    private String image;
    private String description;


    private List<BodyPartIdDTO> bodyParts;

    private EquipmentIdDTO equipment;

    private List<MusclePercentIdDTO> muscles;
}
