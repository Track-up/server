package com.gimnsio.libreta.DTO.exercises;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ExerciseDTO {

    private Long id;
    private String name;
    private String gifUrl;
    private String bodyPart;
    private String target;
    private String equipment;

}
