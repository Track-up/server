package com.gimnsio.libreta.services;

import com.gimnsio.libreta.DTO.workout.WorkoutDTO;
import com.gimnsio.libreta.persistence.entities.WorkoutEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface WorkoutService {

    public Set<WorkoutEntity> getAllWorkouts(Pageable pageable);

    public WorkoutDTO startTraining (Long routineId, Long userId);

}
