package com.gimnsio.libreta.serie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SerieForWorkoutDTO {
    private Long id;
    private String exercise;
    private int reps;
    private double kg;
}
