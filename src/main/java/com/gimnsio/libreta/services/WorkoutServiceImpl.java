package com.gimnsio.libreta.services;

import com.gimnsio.libreta.DTO.exercises.ExerciseForWorkoutDTO;
import com.gimnsio.libreta.DTO.exercises.ExerciseMinimalDTO;
import com.gimnsio.libreta.DTO.routines.RoutineForWorkoutDTO;
import com.gimnsio.libreta.DTO.serie.SerieForExerciseDTO;
import com.gimnsio.libreta.DTO.workout.WorkoutDTO;
import com.gimnsio.libreta.Mapper.WorkoutMapper;
import com.gimnsio.libreta.persistence.entities.ExerciseEntity;
import com.gimnsio.libreta.persistence.entities.SerieEntity;
import com.gimnsio.libreta.persistence.entities.WorkoutEntity;
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
        workout.setCreationDate(new Date());
        workout.setWorker(userService.getUserEntityById(userId));
        workout = workoutRepository.save(workout);
        WorkoutDTO workoutDTO = workoutMapper.entityToDTO(workout);
        List<ExerciseForWorkoutDTO> exerciseForWorkoutDTOs = new ArrayList<>();

        for (Long exerciseId : exercisesIds) {
            ExerciseEntity exercise = exerciseService.getExerciseById(exerciseId);
            List<SerieForExerciseDTO> series = new ArrayList<>();
            List<SerieEntity> seriesEntity = serieService.getSeriesOfLastWorkoutFromExerciseAndUser(exerciseId, workout.getWorker().getId());

            if (seriesEntity.isEmpty()) {//TODO aqui le paso un ex4wo con series null y me devolverá un kg para modificar :) MUY MEJORABLE
                for (int i = 0; i < 3; i++) {
                    SerieEntity serieEntity = new SerieEntity();
                    serieEntity.setKg(50);
                    serieEntity.setReps(10);
                    serieEntity.setExercise(exercise);
                    serieEntity.setWorkout(workout);
                    series.add(serieService.saveSerie(serieEntity));
                }

            } else {
                if (difficulty == 1) {
                    for (SerieEntity serie : seriesEntity) {
                        SerieEntity serieEntity = new SerieEntity();
                        serieEntity.setKg(serie.getKg());
                        serieEntity.setReps(serie.getReps());
                        serieEntity.setExercise(exercise);
                        serieEntity.setWorkout(workout);
                        series.add(serieService.saveSerie(serieEntity));
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
                        serieEntity.setExercise(exercise);
                        serieEntity.setWorkout(workout);
                        series.add(serieService.saveSerie(serieEntity));
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
                        serieEntity.setExercise(exercise);
                        serieEntity.setWorkout(workout);
                        series.add(serieService.saveSerie(serieEntity));
                    }
                }

            }
            exerciseForWorkoutDTOs.add(new ExerciseForWorkoutDTO(exercise.getName(),exercise.getGifUrl(),series));
        }

        workoutDTO.setExercisesOfWorkout(exerciseForWorkoutDTOs);
        return workoutDTO;

    }

    @Override
    public WorkoutDTO startWorkout(WorkoutDTO workoutDTO) {
        WorkoutEntity existingWorkout = updateWorkout(workoutDTO);
        existingWorkout.setStartDate(new Date());
        workoutDTO.setStartDate(new Date());
        workoutRepository.save(existingWorkout);
        return workoutDTO;
    }

    private WorkoutEntity updateWorkout(WorkoutDTO workoutDTO) {
        WorkoutEntity existingWorkout = null;
        try {
            existingWorkout = getWorkout(workoutDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<ExerciseForWorkoutEntity> exercisesFotWorkoutEntityFromDTO = new ArrayList<>(); 


        List<SerieForExerciseDTO> seriesDTO = new ArrayList<>();
        for (int i = 0; i < workoutDTO.getExercisesOfWorkout().size(); i++) {

            Optional<ExerciseForWorkoutEntity> exerciseForWorkoutEntityOptional = exerciseForWorkoutRepository.findById(workoutDTO.getExercisesOfWorkout().get(i).getId());
            if (exerciseForWorkoutEntityOptional.isPresent()) {
                exercisesFotWorkoutEntityFromDTO.add(exerciseForWorkoutEntityOptional.get());
            } else {
                //Crea uno
            }
            List<SerieEntity> series = serieService.getSeriesOfExerciseForWorkout(exerciseForWorkoutEntityOptional.get());
            for (int j = 0; j < workoutDTO.getExercisesOfWorkout().get(i).getSeries().size(); j++) {
                if (series.size()>=j){
                    series.get(j).setKg(workoutDTO.getExercisesOfWorkout().get(i).getSeries().get(j).getKg());
                    series.get(j).setReps(workoutDTO.getExercisesOfWorkout().get(i).getSeries().get(j).getReps());

                }else {
                    SerieEntity serie = new SerieEntity(exerciseForWorkoutEntityOptional.get(), workoutDTO.getExercisesOfWorkout().get(i).getSeries().get(j).getReps(), workoutDTO.getExercisesOfWorkout().get(i).getSeries().get(j).getKg());
                }
                seriesDTO.add(serieService.saveSerie(series.get(j)));
            }
        }
        return existingWorkout;
    }

    private WorkoutEntity getWorkout(WorkoutDTO workoutDTO) throws Exception {
        Optional<WorkoutEntity> optionalWorkout = workoutRepository.findById(workoutDTO.getId());
        if (!optionalWorkout.isEmpty()) {
            return optionalWorkout.get();
        } throw new Exception();

//        WorkoutEntity existingWorkout = workoutRepository.findById(updatedWorkout.getId())
//                .orElseThrow(() -> new EntityNotFoundException("Workout not found"));
    }

    @Override
    public WorkoutDTO endWorkout(WorkoutDTO workoutDTO) {
        WorkoutEntity existingWorkout = updateWorkout(workoutDTO);
        existingWorkout.setEndDate(new Date());
        workoutRepository.save(existingWorkout);
        workoutDTO.setEndDate(new Date());
        return workoutDTO;
    }
}
