package com.gimnsio.libreta.services;

import com.gimnsio.libreta.DTO.exercises.ExerciseForRoutineDTO;
import com.gimnsio.libreta.DTO.exercises.ExerciseNewDTO;
import com.gimnsio.libreta.persistence.entities.ExerciseEntity;
import com.gimnsio.libreta.persistence.entities.RoutineEntity;
import com.gimnsio.libreta.persistence.specification.ExerciseSpecification;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Locale;
import java.util.Set;

public interface ExerciseService {

    public List<ExerciseEntity> getAllExercises(Pageable pageable, Locale locale);

    public List<ExerciseEntity> getExercises(ExerciseSpecification exerciseSpecification,Pageable pageable, Locale locale);


    public ExerciseEntity getExerciseById(String id);

    public ExerciseEntity updateExercise(String exerciseId, ExerciseEntity updatedExercise);

    public ExerciseEntity createExercise(ExerciseNewDTO exercise);

    public void deleteExercise(Long id);


    public Set<ExerciseEntity> createExercises (Set<ExerciseEntity> entities);

    public List<ExerciseForRoutineDTO> getExercisesForRoutine(RoutineEntity routine);

    public List<ExerciseEntity> getExercisesByName(String name, Pageable pageable, Locale locale);

    public List<ExerciseEntity> getExercisesByForce(String force, Pageable pageable, Locale userLocale);

    public List<ExerciseEntity> getExercisesByLevel(String level, Pageable pageable, Locale userLocale);

    public List<ExerciseEntity> getExercisesByMechanic(String mechanic, Pageable pageable, Locale userLocale);

    public List<ExerciseEntity> getExercisesByEquipment(String equipment, Pageable pageable, Locale userLocale);

    public List<ExerciseEntity> getExercisesByCategory(String category, Pageable pageable, Locale userLocale);

    public List<ExerciseEntity> getExercisesByMuscle(String muscle, Pageable pageable, Locale userLocale);


    //FOR WORKOUT
//    public ExerciseForWorkoutDTO getExerciseForWorkout(ExerciseForWorkoutEntity exerciseForWorkoutEntity);
}
