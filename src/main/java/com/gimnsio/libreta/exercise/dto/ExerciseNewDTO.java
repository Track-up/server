package com.gimnsio.libreta.exercise.dto;

import com.gimnsio.libreta.DTO.equipment.EquipmentIdDTO;
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
    private EquipmentIdDTO equipment;
    private List<MusclePercentIdDTO> muscles;
}
