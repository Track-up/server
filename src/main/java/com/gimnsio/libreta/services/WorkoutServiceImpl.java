package com.gimnsio.libreta.services;

import com.gimnsio.libreta.DTO.exercises.ExerciseForWorkoutDTO;
import com.gimnsio.libreta.DTO.exercises.ExerciseMinimalDTO;
import com.gimnsio.libreta.DTO.routines.RoutineForWorkoutDTO;
import com.gimnsio.libreta.DTO.serie.SerieForExerciseDTO;
import com.gimnsio.libreta.DTO.workout.WorkoutDTO;
import com.gimnsio.libreta.Mapper.WorkoutMapper;
import com.gimnsio.libreta.persistence.entities.ExerciseForWorkoutEntity;
import com.gimnsio.libreta.persistence.entities.SerieEntity;
import com.gimnsio.libreta.persistence.entities.WorkoutEntity;
import com.gimnsio.libreta.persistence.repositories.ExerciseForWorkoutRepository;
import com.gimnsio.libreta.persistence.repositories.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private ExerciseForWorkoutRepository exerciseForWorkoutRepository;

    @Override
    public Set<WorkoutEntity> getAllWorkouts(Pageable pageable) {
        return workoutRepository.findAll(pageable).stream().collect(Collectors.toSet());//TODO NOT TESTED
    }

    @Override
    public WorkoutDTO createWorkout(Long routineId, Long userId, Long difficulty) {

        RoutineForWorkoutDTO routine = routineService.getRoutineForWorkout(routineId);

        List<Long> exercisesIds = new ArrayList<>();
        for (ExerciseMinimalDTO exercise : routine.getExercises()) {
            exercisesIds.add(exercise.getId());
        }

        WorkoutEntity workout = new WorkoutEntity();
        workout.setDate(new Date());
        workout.setWorker(userService.getUserEntityById(userId));
        workout = workoutRepository.save(workout);
        WorkoutDTO workoutDTO = workoutMapper.entityToDTO(workout);
        List<ExerciseForWorkoutDTO> exerciseForWorkoutDTOs = new ArrayList<>();

        for (Long exerciseId : exercisesIds) {
            ExerciseForWorkoutEntity exerciseForWorkout = new ExerciseForWorkoutEntity();
            List<SerieForExerciseDTO> series = new ArrayList<>();
            exerciseForWorkout.setExercise(exerciseService.getExerciseById(exerciseId));//mejorable?
            exerciseForWorkout.setWorkout(workout);


            List<SerieEntity> seriesEntity = serieService.getSeriesOfLastWorkoutFromExerciseAndUser(exerciseId, workout.getWorker().getId());
            exerciseForWorkout = exerciseForWorkoutRepository.save(exerciseForWorkout);
            if (seriesEntity.isEmpty()) {//TODO aqui le paso un ex4wo con series null y me devolverá un kg para modificar :)
                for (int i = 0; i < 3; i++) {
                    SerieEntity serieEntity = new SerieEntity();
                    serieEntity.setKg(50);
                    serieEntity.setReps(10);
                    serieEntity.setExerciseForWorkout(exerciseForWorkout);
                    series.add(serieService.createSerie(serieEntity));
                }

            } else {
                if (difficulty == 1) {
                    for (SerieEntity serie : seriesEntity) {
                        SerieEntity serieEntity = new SerieEntity();
                        serieEntity.setKg(serie.getKg());
                        serieEntity.setReps(serie.getReps());
                        serieEntity.setExerciseForWorkout(exerciseForWorkout);
                        series.add(serieService.createSerie(serieEntity));
                    }
                } else if (difficulty == 2) {
                    for (SerieEntity serie : seriesEntity) {
                        SerieEntity serieEntity = new SerieEntity();
                        if (serie.getReps() > 9) {
                            serieEntity.setKg(serie.getKg() + 1);
                            serieEntity.setReps(6);
                        } else {
                            serieEntity.setKg(serie.getKg());
                            serieEntity.setReps(serie.getReps() + 1);
                        }
                        serieEntity.setExerciseForWorkout(exerciseForWorkout);
                        series.add(serieService.createSerie(serieEntity));
                    }
                } else if (difficulty == 3) {
                    for (SerieEntity serie : seriesEntity) {
                        SerieEntity serieEntity = new SerieEntity();
                        if (serie.getReps() > 8) {
                            serieEntity.setKg(serie.getKg() + 2.5);
                            serieEntity.setReps(6);
                        } else {
                            serieEntity.setKg(serie.getKg());
                            serieEntity.setReps(serie.getReps() + 2);
                        }
                        serieEntity.setExerciseForWorkout(exerciseForWorkout);
                        series.add(serieService.createSerie(serieEntity));
                    }
                }

            }

            ExerciseForWorkoutDTO exercise = exerciseService.getExerciseForWorkout(exerciseForWorkout);
            exerciseForWorkout.getExercise().getGifUrl();
            exercise.setSeries(series);

            exerciseForWorkoutDTOs.add(exercise);

        }

        workoutDTO.setExercisesOfWorkout(exerciseForWorkoutDTOs);
        return workoutDTO;

    }
}
