package com.gimnsio.libreta.user.dto;

import com.gimnsio.libreta.workout.persistence.WorkoutEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserForWorkoutDTO {

    private long id;
    private Set<WorkoutEntity> workouts_done;

}
