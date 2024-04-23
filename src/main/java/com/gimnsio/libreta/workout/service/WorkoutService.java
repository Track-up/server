package com.gimnsio.libreta.workout.service;

import com.gimnsio.libreta.workout.dto.WorkoutDTO;
import com.gimnsio.libreta.workout.persistence.WorkoutEntity;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface WorkoutService {

    public Set<WorkoutEntity> getAllWorkouts(Pageable pageable);

    public WorkoutDTO createWorkout (Long routineId, Long userId, Long difficulty);

    public WorkoutDTO startWorkout(WorkoutDTO workoutDTO);

    public WorkoutDTO endWorkout(WorkoutDTO workoutDTO);

    WorkoutDTO getWorkoutById(Long id);

    List<WorkoutDTO> getLastWorkouts(Long userId, Integer until);

    public List<WorkoutDTO> getLastWorkoutsOfUserByExercise(Long userId, Date until, String exerciseId);




}
