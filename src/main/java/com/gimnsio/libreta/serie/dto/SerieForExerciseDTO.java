package com.gimnsio.libreta.serie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SerieForExerciseDTO {
    private Long id;
    private long reps;
    private double kg;
}
