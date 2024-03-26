package com.gimnsio.libreta.exercise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ExerciseForRoutineDTO {

    private String id;
    private String name;
    private List<String> images;
    private String description;
    private Long numSeries;

}