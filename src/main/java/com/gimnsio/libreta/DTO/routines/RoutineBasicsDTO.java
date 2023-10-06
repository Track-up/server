package com.gimnsio.libreta.DTO.routines;

import com.gimnsio.libreta.DTO.exercises.ExerciseForRoutineDTO;
import com.gimnsio.libreta.DTO.exercises.ExerciseMinimalDTO;
import com.gimnsio.libreta.DTO.users.UserForRoutineDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RoutineBasicsDTO {

    private Long id;
    private String name;
//    private int numExercises;
    private List<ExerciseForRoutineDTO> exercises;
    private UserForRoutineDTO creator;
    private String image;

}
