package com.gimnsio.libreta.DTO.serie;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SerieForExerciseDTO {
    private Long id;
    private long reps;
    private double kg;
}
