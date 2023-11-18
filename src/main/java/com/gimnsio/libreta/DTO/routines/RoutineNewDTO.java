package com.gimnsio.libreta.DTO.routines;

import com.gimnsio.libreta.DTO.exercises.ExerciseForNewRoutineDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RoutineNewDTO {

    private String name;
    private String image;
    private List<ExerciseForNewRoutineDTO> exercises;
    private Long creatorId;
    private boolean isPublic;

}
