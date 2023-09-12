package com.gimnsio.libreta.DTO.users;

import com.gimnsio.libreta.persistence.entities.WorkoutEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserForWorkoutDTO {

    private long id;
    private Set<WorkoutEntity> workouts_done;

}
