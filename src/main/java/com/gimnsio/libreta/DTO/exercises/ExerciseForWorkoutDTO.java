package com.gimnsio.libreta.DTO.exercises;

import com.gimnsio.libreta.DTO.serie.SerieForExerciseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ExerciseForWorkoutDTO {

    private String name;
    private String image;
    private List<SerieForExerciseDTO> series;
}
