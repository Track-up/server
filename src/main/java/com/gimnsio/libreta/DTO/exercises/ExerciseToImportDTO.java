package com.gimnsio.libreta.DTO.exercises;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExerciseToImportDTO {

    private String id;
    private String name;
    private String gifUrl;
    private String bodyPart;
    private String equipment;
    private String target;

}
