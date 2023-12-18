package com.gimnsio.libreta.services;

import com.gimnsio.libreta.DTO.exercises.ExerciseDTO;
import com.gimnsio.libreta.DTO.exercises.ExerciseNewDTO;
import com.gimnsio.libreta.persistence.entities.ExerciseEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Locale;
import java.util.Set;

public interface ExerciseService {

    public List<ExerciseEntity> getAllExercises(Pageable pageable, Locale locale);


    public ExerciseEntity getExerciseById(Long id);

//    public Exercise updateExercise(Long exerciseId, Exercise updatedExercise);

    public ExerciseEntity createExercise(ExerciseNewDTO exercise);

    public void deleteExercise(Long id);

//    public List<Exercise> getExercisesByMuscle(Long muscle_id);

    public Set<ExerciseEntity> createExercises (Set<ExerciseEntity> entities);

    public Set<ExerciseDTO> getExercisesByBodyPart(Long id);

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
