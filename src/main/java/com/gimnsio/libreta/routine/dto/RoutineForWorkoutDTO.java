package com.gimnsio.libreta.routine.dto;

import com.gimnsio.libreta.exercise.dto.ExerciseMinimalDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RoutineForWorkoutDTO {
    private Long id;
    private List<ExerciseMinimalDTO> exercises;
}
