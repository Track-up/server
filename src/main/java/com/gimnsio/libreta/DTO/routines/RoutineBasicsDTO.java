package com.gimnsio.libreta.DTO.routines;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoutineBasicsDTO {

    private Long id;
    private String name;
    private int numExercises;
    private String creator;
    private String image;

}
