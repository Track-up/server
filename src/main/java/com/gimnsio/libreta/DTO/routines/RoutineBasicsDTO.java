package com.gimnsio.libreta.DTO.routines;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class RoutineBasicsDTO {

    private Long id;
    private String name;
    private int numExercises;
    private String creator;
    private Date dateOfCreation;
    private int durationMinutes;

}
