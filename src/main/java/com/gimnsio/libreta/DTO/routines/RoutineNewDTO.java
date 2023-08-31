package com.gimnsio.libreta.DTO.routines;

import com.gimnsio.libreta.DTO.exercises.ExerciseMinimalDTO;
import com.gimnsio.libreta.DTO.users.UserIdDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RoutineNewDTO {

    private String name;
    private List<ExerciseMinimalDTO> exercises;
    private UserIdDTO creator;

}
