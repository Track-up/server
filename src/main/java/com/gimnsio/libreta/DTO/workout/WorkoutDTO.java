package com.gimnsio.libreta.DTO.workout;

import com.gimnsio.libreta.DTO.exercises.ExerciseForWorkoutDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class WorkoutDTO {
    @NotEmpty
    private Long id;
    @NotEmpty
    private List<ExerciseForWorkoutDTO> exercises;
    private Date startDate;
    private Date endDate;
}
