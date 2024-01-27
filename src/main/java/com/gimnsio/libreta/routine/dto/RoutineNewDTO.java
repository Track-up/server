package com.gimnsio.libreta.routine.dto;

import com.gimnsio.libreta.DTO.exercises.ExerciseForNewRoutineDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RoutineNewDTO {

    @NotBlank
    private String name;
    private String image;
    @NotEmpty
    private List<ExerciseForNewRoutineDTO> exercises;

    private Long creatorId;
    private boolean isPublic;

}
