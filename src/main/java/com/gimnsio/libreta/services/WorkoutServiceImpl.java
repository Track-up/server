package com.gimnsio.libreta.services;

import com.gimnsio.libreta.DTO.exercises.ExerciseMinimalDTO;
import com.gimnsio.libreta.DTO.routines.RoutineForWorkoutDTO;
import com.gimnsio.libreta.DTO.workout.WorkoutDTO;
import com.gimnsio.libreta.Mapper.WorkoutMapper;
import com.gimnsio.libreta.persistence.entities.SerieEntity;
import com.gimnsio.libreta.persistence.entities.WorkoutEntity;
import com.gimnsio.libreta.persistence.repositories.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WorkoutServiceImpl implements WorkoutService {

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
    public WorkoutDTO startTraining(Long routineId, Long userId) {
        RoutineForWorkoutDTO routine = routineService.getRoutineForWorkout(routineId);
        List<Long> exercisesIds = new ArrayList<>();

        for (ExerciseMinimalDTO exercise : routine.getExercises()) {
            exercisesIds.add(exercise.getId());
        }

        return createWorkout(exercisesIds, userId);


//        UserForWorkoutDTO user = userService.getUserById(userId);
    }

    private WorkoutDTO createWorkout(List<Long> exercisesIds, Long userId) {
        WorkoutEntity workout = new WorkoutEntity();
        workout.setDate(new Date());
        workout.setWorker(userService.getUserEntityById(userId));
        workoutRepository.save(workout);

        List<SerieEntity> series = new ArrayList<>();
        for (Long exerciseId : exercisesIds) {
            List<SerieEntity> seriesFromRepository = serieService.getSeriesOfLastWorkoutFromExerciseAndUser(userId, exerciseId);
            if (seriesFromRepository.isEmpty()) {
                for (int i = 0; i < 3; i++) {
                    SerieEntity serieEntity = new SerieEntity();
                    serieEntity.setExercise(exerciseService.getExerciseById(exerciseId));
                    serieEntity.setKg(50);
                    serieEntity.setReps(10);
                    serieEntity.setWorkout(workout);
                    series.add(serieService.createSerieEntity(serieEntity));
                }
            } else {
                for (SerieEntity serie : seriesFromRepository) {
                    SerieEntity serieEntity = new SerieEntity();
                    serieEntity.setExercise(exerciseService.getExerciseById(exerciseId));
                    serieEntity.setKg(serie.getKg()+1);
                    serieEntity.setReps(serie.getReps());
                    serieEntity.setWorkout(workout);
                    series.add(serieEntity);
                }
            }
        }
        WorkoutDTO workoutDTO = workoutMapper.entityToDTO(workout);
        workoutDTO.setExercisesOfWorkout(series);
        return workoutDTO;
    }
}
