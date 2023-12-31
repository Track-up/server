package com.gimnsio.libreta.services;

import com.gimnsio.libreta.DTO.exercises.ExerciseDTO;
import com.gimnsio.libreta.DTO.exercises.ExerciseNewDTO;
import com.gimnsio.libreta.DTO.exercises.ExerciseToImportDTO;
import com.gimnsio.libreta.domain.Exercise;
import com.gimnsio.libreta.persistence.entities.ExerciseEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface ExerciseService {

    public List<ExerciseDTO> getAllExercises(Pageable pageable);


    public ExerciseEntity getExerciseById(Long id);

    public Exercise updateExercise(Long exerciseId, Exercise updatedExercise);

    public ExerciseEntity createExercise(ExerciseNewDTO exercise);

    public void deleteExercise(Long id);

    public List<Exercise> getExercisesByMuscle(Long muscle_id);

    public Set<ExerciseEntity> createExercises (Set<ExerciseToImportDTO> exercisesToImportDTO);

    public Set<ExerciseDTO> getExercisesByBodyPart(Long id);

    public Set<ExerciseDTO> getExercisesByName(String name, Pageable pageable);


    //FOR WORKOUT
//    public ExerciseForWorkoutDTO getExerciseForWorkout(ExerciseForWorkoutEntity exerciseForWorkoutEntity);
}
