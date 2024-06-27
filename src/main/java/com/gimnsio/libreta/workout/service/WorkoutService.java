package com.gimnsio.libreta.workout.service;

import com.gimnsio.libreta.workout.dto.WorkoutDTO;
import com.gimnsio.libreta.workout.persistence.WorkoutEntity;
import org.springframework.data.domain.Pageable;

import java.util.*;

public interface WorkoutService {

    public Set<WorkoutEntity> getAllWorkouts(Pageable pageable);

    public WorkoutDTO createWorkoutByRoutine(Long routineId, Long userId, Long difficulty);

    public WorkoutDTO createWorkoutByWorkout(Long workoutId, Long userId, Long difficulty);

    public WorkoutDTO startWorkout(WorkoutDTO workoutDTO);

    public HashMap endWorkout(WorkoutDTO workoutDTO);

    WorkoutDTO getWorkoutById(Long id);

    List<WorkoutDTO> getLastWorkouts(Long userId, Integer until);

    public List<WorkoutDTO> getLastWorkoutsOfUserByExercise(Long userId, Date until, String exerciseId);

    HashMap<String,String> getWorkoutStats(Long id);
}
