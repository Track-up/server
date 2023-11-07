package com.gimnsio.libreta.DTO.routines;

import com.gimnsio.libreta.DTO.exercises.ExerciseForRoutineDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RoutineNewDTO {

    private String name;
    private String image;
    private List<ExerciseForRoutineDTO> exercises;
    private Long creatorId;
    private boolean isPublic;

}
