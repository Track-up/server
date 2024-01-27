package com.gimnsio.libreta.DTO.exercises;

import com.gimnsio.libreta.serie.dto.SerieForExerciseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseForWorkoutDTO {

    private String name;
    private List<String> images;
    private String exerciseId;
    private List<SerieForExerciseDTO> series;
}
