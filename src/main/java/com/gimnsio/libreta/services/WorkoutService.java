package com.gimnsio.libreta.services;

import com.gimnsio.libreta.persistence.entities.WorkoutEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WorkoutService {

    public Page<WorkoutEntity> getAllWorkouts(Pageable pageable);

    public WorkoutEntity startTraining (Long routineId, Long userId);

}
