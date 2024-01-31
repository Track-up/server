package com.gimnsio.libreta.routine.dto;

import com.gimnsio.libreta.exercise.dto.ExerciseForRoutineDTO;
import com.gimnsio.libreta.user.dto.UserForRoutineDTO;
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
