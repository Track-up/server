package com.gimnsio.libreta.DTO.exercises;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExerciseForRoutineDTO {

    private Long id;
    private String name;
    private String gifUrl;
    private String description;
    private Long numSeries;

}
