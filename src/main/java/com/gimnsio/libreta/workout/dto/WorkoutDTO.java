package com.gimnsio.libreta.workout.dto;

import com.gimnsio.libreta.exercise.dto.ExerciseForWorkoutDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class WorkoutDTO {
    
    private Long id;
    @NotEmpty
    private List<ExerciseForWorkoutDTO> exercises;
    private Date creationDate;
    private Date startDate;
    private Date endDate;
}
