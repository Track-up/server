package com.gimnsio.libreta.DTO.workout;

import com.gimnsio.libreta.persistence.entities.SerieEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class WorkoutDTO {

    private Long id;
    private List<SerieEntity> exercisesOfWorkout;
    private Date date;
}
