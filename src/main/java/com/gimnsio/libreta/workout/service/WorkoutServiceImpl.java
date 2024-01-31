package com.gimnsio.libreta.workout.service;

import com.gimnsio.libreta.exercise.dto.ExerciseForRoutineDTO;
import com.gimnsio.libreta.exercise.dto.ExerciseForWorkoutDTO;
import com.gimnsio.libreta.routine.dto.RoutineDTO;
import com.gimnsio.libreta.serie.dto.SerieForExerciseDTO;
import com.gimnsio.libreta.exercise.service.ExerciseService;
import com.gimnsio.libreta.routine.service.RoutineService;
import com.gimnsio.libreta.serie.service.SerieService;
import com.gimnsio.libreta.user.service.UserService;
import com.gimnsio.libreta.workout.dto.WorkoutDTO;
import com.gimnsio.libreta.workout.mapper.WorkoutMapper;
import com.gimnsio.libreta.exercise.persistence.ExerciseEntity;
import com.gimnsio.libreta.serie.persistence.SerieEntity;
import com.gimnsio.libreta.workout.persistence.WorkoutEntity;
import com.gimnsio.libreta.workout.persistence.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private WorkoutMapper workoutMapper;

    @Autowired
    private RoutineService routineService;

    @Autowired
    private UserService userService;

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private SerieService serieService;


    @Override
    public Set<WorkoutEntity> getAllWorkouts(Pageable pageable) {
        return workoutRepository.findAll(pageable).stream().collect(Collectors.toSet());//TODO NOT TESTED
    }

    @Override
    public List<WorkoutDTO> getLastWorkouts(Long userId, Integer until) {
        List<WorkoutEntity> workouts = workoutRepository.findLastWorkouts(userId,until);
        List<WorkoutDTO> workoutDTOS = workouts.stream().map(workoutMapper::entityToDTO).collect(Collectors.toList());
        workoutDTOS.forEach(workoutDTO -> workoutDTO.setExercises(serieService.getSeriesOfExerciseForWorkout(workoutRepository.findById(workoutDTO.getId()).orElse(null))));
        return workoutDTOS;
    }

    @Override
    public WorkoutDTO createWorkout(Long routineId, Long userId, Long difficulty) {
        //Busqueda de rutina
        RoutineDTO routineDTO = routineService.getRoutineById(routineId);

        //Creación de workout
        WorkoutEntity workout = new WorkoutEntity();
        workout.setCreationDate(new Date());
        workout.setWorker(userService.getUserEntityById(userId));
        workout = workoutRepository.save(workout);
        WorkoutDTO workoutDTO = workoutMapper.entityToDTO(workout);


        //Logica de series :)=)( y creación del DTO
        List<ExerciseForWorkoutDTO> exerciseForWorkoutDTOS = new ArrayList<>();
        for (ExerciseForRoutineDTO exerciseForRoutineDTO : routineDTO.getExercises()) {
            List<SerieForExerciseDTO> seriesForExerciseDTO = new ArrayList<>();
            ExerciseEntity exercise = exerciseService.getExerciseById(exerciseForRoutineDTO.getId());
            List<SerieEntity> series = serieService.getSeriesOfLastWorkoutFromExerciseAndUser(exercise.getId(), userId);//trae todas las series
            if (!series.isEmpty()) {
                for (int i = 0; i < exerciseForRoutineDTO.getNumSeries(); i++) {
                    if (i < series.size() && series.get(i) != null) {
                        seriesForExerciseDTO.add(serieService.createSerie(exercise, difficulty, series.get(i), workout));
                    } else {
                        seriesForExerciseDTO.add(serieService.saveSerie(new SerieEntity(exercise, seriesForExerciseDTO.get(seriesForExerciseDTO.size() - 1).getReps(), seriesForExerciseDTO.get(seriesForExerciseDTO.size() - 1).getKg(), workout)));
                    }
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    seriesForExerciseDTO.add(serieService.saveSerie(new SerieEntity(exercise, 0L, (double) 0, workout)));
                }
            }
            exerciseForWorkoutDTOS.add(new ExerciseForWorkoutDTO(exercise.getName(), exercise.getImages(), exercise.getId(), seriesForExerciseDTO));
        }
        workoutDTO.setExercises(exerciseForWorkoutDTOS);
        return workoutDTO;


//        List<Long> exercisesIds = new ArrayList<>();
//        for (ExerciseForRoutineDTO exercise : routine.getExercises()) {
//            exercisesIds.add(exercise.getId());
//        }
//
//        WorkoutEntity workout = new WorkoutEntity();
//        workout.setCreationDate(new Date());
//        workout.setWorker(userService.getUserEntityById(userId));
//        workout = workoutRepository.save(workout);
//        WorkoutDTO workoutDTO = workoutMapper.entityToDTO(workout);
//        List<ExerciseForWorkoutDTO> exerciseForWorkoutDTOs = new ArrayList<>();
//
//
//
//
//        for (Long exerciseId : exercisesIds) {
//            ExerciseEntity exercise = exerciseService.getExerciseById(exerciseId);
//            List<SerieForExerciseDTO> series = new ArrayList<>();
//            List<SerieEntity> seriesEntity = serieService.getSeriesOfLastWorkoutFromExerciseAndUser(exerciseId, workout.getWorker().getId());
//
//            if (seriesEntity.isEmpty()) {//TODO aqui le paso un ex4wo con series null y me devolverá un kg para modificar :) MUY MEJORABLE
//                for (int i = 0; i < 3; i++) {
//                    SerieEntity serieEntity = new SerieEntity();
//                    serieEntity.setKg(50);
//                    serieEntity.setReps(10);
//                    serieEntity.setExercise(exercise);
//                    serieEntity.setWorkout(workout);
//                    series.add(serieService.saveSerie(serieEntity));
//                }
//
//            } else {
//                if (difficulty == 1) {
//                    for (SerieEntity serie : seriesEntity) {
//                        SerieEntity serieEntity = new SerieEntity();
//                        serieEntity.setKg(serie.getKg());
//                        serieEntity.setReps(serie.getReps());
//                        serieEntity.setExercise(exercise);
//                        serieEntity.setWorkout(workout);
//                        series.add(serieService.saveSerie(serieEntity));
//                    }
//                } else if (difficulty == 2) {
//                    for (SerieEntity serie : seriesEntity) {
//                        SerieEntity serieEntity = new SerieEntity();
//                        if (serie.getReps() > 9) {
//                            serieEntity.setKg(serie.getKg() + 1);
//                            serieEntity.setReps(6);
//                        } else {
//                            serieEntity.setKg(serie.getKg());
//                            serieEntity.setReps(serie.getReps() + 1);
//                        }
//                        serieEntity.setExercise(exercise);
//                        serieEntity.setWorkout(workout);
//                        series.add(serieService.saveSerie(serieEntity));
//                    }
//                } else if (difficulty == 3) {
//                    for (SerieEntity serie : seriesEntity) {
//                        SerieEntity serieEntity = new SerieEntity();
//                        if (serie.getReps() > 8) {
//                            serieEntity.setKg(serie.getKg() + 2.5);
//                            serieEntity.setReps(6);
//                        } else {
//                            serieEntity.setKg(serie.getKg());
//                            serieEntity.setReps(serie.getReps() + 2);
//                        }
//                        serieEntity.setExercise(exercise);
//                        serieEntity.setWorkout(workout);
//                        series.add(serieService.saveSerie(serieEntity));
//                    }
//                }
//
//            }
//            exerciseForWorkoutDTOs.add(new ExerciseForWorkoutDTO(exercise.getName(),exercise.getGifUrl(),exerciseId,series));
//        }
//
//        workoutDTO.setExercisesOfWorkout(exerciseForWorkoutDTOs);
//        return workoutDTO;
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

        for (ExerciseForWorkoutDTO exerciseDTO : workoutDTO.getExercises()) {
            List<SerieEntity> series = existingWorkout.getSeries();//serieService.getSeriesOfLastWorkoutFromExerciseAndUser(exerciseDTO.getExerciseId(), existingWorkout.getWorker().getId());
            ExerciseEntity exercise = exerciseService.getExerciseById(exerciseDTO.getExerciseId());
            updateSeries(series, exerciseDTO, exercise, existingWorkout);
        }
        return existingWorkout;
    }

    private void updateSeries(List<SerieEntity> seriesEntity, ExerciseForWorkoutDTO exerciseDTO, ExerciseEntity exercise, WorkoutEntity workout) {
        Iterator<SerieEntity> iterator = seriesEntity.iterator();

        // Borra y actualiza
        while (iterator.hasNext()) {
            SerieEntity serieEntity = iterator.next();
            SerieForExerciseDTO serieDTO = exerciseDTO.getSeries().stream().filter(s -> s.getId().equals(serieEntity.getId())).findFirst().orElse(null);

            if (serieDTO != null) {
                serieEntity.setReps(serieDTO.getReps());
                serieEntity.setKg(serieDTO.getKg());
            } else {
                iterator.remove();
                serieService.deleteSerie(serieEntity);
            }
        }

        // Añade nuevos
        for (int i = 0; i < exerciseDTO.getSeries().size(); i++) {
            SerieForExerciseDTO finalSerieDTO = exerciseDTO.getSeries().get(i);
            if (seriesEntity.stream().noneMatch(s -> s.getId().equals(finalSerieDTO.getId()))) {
                SerieEntity newSerie = new SerieEntity(exercise, exerciseDTO.getSeries().get(i).getReps(), exerciseDTO.getSeries().get(i).getKg(), workout);
                exerciseDTO.getSeries().get(i).setId(serieService.saveSerie(newSerie).getId());
                newSerie.setId(exerciseDTO.getSeries().get(i).getId());
                seriesEntity.add(newSerie);
            }
        }
        for (SerieForExerciseDTO serieDTO : exerciseDTO.getSeries()) {
            SerieForExerciseDTO finalSerieDTO = serieDTO;
            if (seriesEntity.stream().noneMatch(s -> s.getId().equals(finalSerieDTO.getId()))) {
                SerieEntity newSerie = new SerieEntity(exercise, serieDTO.getReps(), serieDTO.getKg(), workout);
                serieDTO = serieService.saveSerie(newSerie);
                newSerie.setId(serieDTO.getId());
                seriesEntity.add(newSerie);
            }
        }

        serieService.saveAll(seriesEntity);
    }

    private WorkoutEntity getWorkout(WorkoutDTO workoutDTO) throws Exception {
        Optional<WorkoutEntity> optionalWorkout = workoutRepository.findById(workoutDTO.getId());
        if (!optionalWorkout.isEmpty()) {
            return optionalWorkout.get();
        }
        throw new Exception();

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

    @Override
    public WorkoutDTO getWorkoutById(Long id) {
        WorkoutEntity workoutEntity = workoutRepository.findById(id).orElse(null);
        WorkoutDTO workoutDTO = workoutMapper.entityToDTO(workoutEntity);
        workoutDTO.setExercises(serieService.getSeriesOfExerciseForWorkout(workoutEntity));
        return workoutDTO;
    }






}
