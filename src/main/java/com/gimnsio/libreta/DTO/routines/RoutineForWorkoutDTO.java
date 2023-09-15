package com.gimnsio.libreta.DTO.routines;

import com.gimnsio.libreta.DTO.exercises.ExerciseMinimalDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RoutineForWorkoutDTO {
    private Long id;
    private List<ExerciseMinimalDTO> exercises;
}
