package com.gimnsio.libreta.routine.dto;

import com.gimnsio.libreta.exercise.dto.ExerciseForNewRoutineDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RoutineEditDTO {

    private long id;
    private String name;
    private String image;
    private List<ExerciseForNewRoutineDTO> exercises;
    private Long creatorId;
    private boolean isPublic;

}