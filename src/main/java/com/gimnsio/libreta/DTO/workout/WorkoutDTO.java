package com.gimnsio.libreta.DTO.workout;

import com.gimnsio.libreta.domain.Serie;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
public class WorkoutDTO {

    private Long id;
    private Set<Serie> exercisesOfWorkout;
    private Date date;
}
