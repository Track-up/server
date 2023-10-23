package com.gimnsio.libreta.DTO.serie;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SerieForExerciseDTO {
    private long id;
    private long reps;
    private double kg;
}
