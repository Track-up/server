package com.gimnsio.libreta.services;

import com.gimnsio.libreta.DTO.exercises.ExerciseMinimalDTO;
import com.gimnsio.libreta.DTO.routines.RoutineForWorkoutDTO;
import com.gimnsio.libreta.Mapper.WorkoutMapper;
import com.gimnsio.libreta.persistence.entities.SerieEntity;
import com.gimnsio.libreta.persistence.entities.WorkoutEntity;
import com.gimnsio.libreta.persistence.repositories.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WorkoutServiceImpl implements WorkoutService{

    @Autowired
    private UserService userService;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private RoutineService routineService;

    @Autowired
    private WorkoutMapper workoutMapper;

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private SerieService serieService;

    @Override
    public Page<WorkoutEntity> getAllWorkouts(Pageable pageable) {
        return workoutRepository.findAll(pageable);
    }

    @Override
    public WorkoutEntity startTraining(Long routineId, Long userId) {
        RoutineForWorkoutDTO routine = routineService.getRoutineForWorkout(routineId);
        List<Long> exercisesIds = new ArrayList<>();

        for (ExerciseMinimalDTO exercise:routine.getExercises()) {
            exercisesIds.add(exercise.getId());
        }

        WorkoutEntity workout =  createWorkout(exercisesIds,userId);
        return workoutRepository.save(workout);

//        UserForWorkoutDTO user = userService.getUserById(userId);




    }

    private WorkoutEntity createWorkout(List<Long> exercisesIds,Long userId) {

        WorkoutEntity workout = new WorkoutEntity();
        workout.setDate(new Date());
        workout.setWorker(userService.getUserEntityById(userId));
        List<SerieEntity> series = new ArrayList<>();
        for (Long exerciseId:exercisesIds) {
            for (int i = 0; i < 3; i++) {
                SerieEntity serieEntity = new SerieEntity();
                serieEntity.setExercise(exerciseService.getExerciseById(exerciseId));
                serieEntity.setKg(70);
                serieEntity.setReps(10);
                series.add(serieService.createSerieEntity(serieEntity));

            }
        }
        workout.setExercisesOfWorkout(series);
        return workout;
    }
}
