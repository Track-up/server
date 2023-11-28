package com.gimnsio.libreta.services;

import com.gimnsio.libreta.DTO.exercises.ExerciseDTO;
import com.gimnsio.libreta.DTO.exercises.ExerciseNewDTO;
import com.gimnsio.libreta.DTO.exercises.ExerciseToImportDTO;
import com.gimnsio.libreta.DTO.muscles.MusclePercentIdDTO;
import com.gimnsio.libreta.Mapper.ExerciseMapper;
import com.gimnsio.libreta.Mapper.MuscleMapper;
import com.gimnsio.libreta.domain.Exercise;
import com.gimnsio.libreta.persistence.entities.ExerciseEntity;
import com.gimnsio.libreta.persistence.entities.MuscleEntity;
import com.gimnsio.libreta.persistence.entities.MuscleForExerciseEntity;
import com.gimnsio.libreta.persistence.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    ExerciseRepository exerciseRepository;

    ExerciseMapper exerciseMapper;

    //OJO QUE VA ALGO CUTRE
    @Autowired
    BodyPartRepository bodyPartRepository;
    @Autowired
    EquipmentRepository equipmentRepository;
    @Autowired
    MuscleRepository muscleRepository;
    @Autowired
    MuscleMapper muscleMapper;
    @Autowired
    MuscleForExerciseRepository muscleForExerciseRepository;


    //AQUÍ ACABA

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ExerciseMapper exerciseMapper) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseMapper = exerciseMapper;
    }

    @Override
    public List<ExerciseDTO> getAllExercises(Pageable pageable) {

        List<ExerciseDTO> exercises = this.exerciseRepository.findAll(pageable).stream().map(exerciseEntity -> {
            return exerciseMapper.entityToDTO(exerciseEntity);
        }).collect(Collectors.toList());
        for (ExerciseDTO exercise: exercises) {
            List<MuscleForExerciseEntity> muscleForExerciseEntity = muscleForExerciseRepository.findByExercise_Id(exercise.getId());
            exercise.setMuscles(muscleMapper.musclePercentEntityToDTO(muscleForExerciseEntity));
        }
        return exercises;
    }

    @Override
    public ExerciseEntity getExerciseById(Long id) {
        Optional<ExerciseEntity> exerciseEntityOptional = exerciseRepository.findById(id);

        if (exerciseEntityOptional.isPresent()) {
            ExerciseEntity exerciseEntity = exerciseEntityOptional.get();
            return exerciseEntity;//exerciseMapper.mapExercise(exerciseEntity);
        } else {
            throw new NoSuchElementException("No se encontró el ejercicio con el ID: " + id);
        }
    }

//    @Override
//    public List<Exercise> getExercisesByMuscle(Long muscleId, Pageable pageable) {
//        return this.exerciseRepository.findByMuscle(muscleId,pageable).stream().map(exerciseEntity -> {
//            return exerciseMapper.mapExercise(exerciseEntity);
//        }).collect(Collectors.toList());
//    }


    @Override
    public Exercise updateExercise(Long id, Exercise exercise) {
        Optional<ExerciseEntity> exerciseEntityOptional = exerciseRepository.findById(id);

        if (exerciseEntityOptional.isPresent()) {
            ExerciseEntity exerciseEntity = exerciseMapper.mapExerciseEntity(exercise);
            exerciseEntity.setId(id);
            return exerciseMapper.mapExercise(exerciseRepository.save(exerciseEntity));
        } else {
            throw new NoSuchElementException("No se encontró el ejercicio con el ID: " + id);
        }
    }

    @Override
    public ExerciseEntity createExercise(ExerciseNewDTO exercise) {

        ExerciseEntity exerciseEntity = exerciseRepository.save(exerciseMapper.newToEntity(exercise));
        for (MusclePercentIdDTO muscle : exercise.getMuscles()) {
            MuscleEntity muscleEntity = new MuscleEntity();
            muscle.setId(muscle.getId());
            MuscleForExerciseEntity muscleForExerciseEntity = new MuscleForExerciseEntity(exerciseEntity,muscleEntity, muscle.getPercent() );
        }

        return exerciseEntity;
    }

    @Override
    public void deleteExercise(Long id) {
        Optional<ExerciseEntity> optionalExerciseEntity = exerciseRepository.findById(id);
        if (optionalExerciseEntity.isPresent()) {
            exerciseRepository.delete(optionalExerciseEntity.get());
        } else {
            throw new NoSuchElementException("No se encontró el ejercicio con el ID: " + id);
        }
    }

    @Override
    public List<Exercise> getExercisesByMuscle(Long muscle_id) {
        return this.exerciseRepository.findByMuscle(muscle_id).stream().map(exerciseEntity -> {
            return exerciseMapper.mapExercise(exerciseEntity);
        }).collect(Collectors.toList());
//        return null;
    }

    public Set<ExerciseEntity> createExercises(Set<ExerciseToImportDTO> exercisesToImportDTO) {
//        Set<ExerciseEntity> exercisesSaved = new HashSet<>();
//        for (ExerciseToImportDTO exercise: exercisesToImportDTO) {
//            ExerciseEntity exerciseEntity = new ExerciseEntity();
//            //Mapeamos manualmente
//            exerciseEntity.setId(Long.parseLong(exercise.getId()));
//            exerciseEntity.setName(exercise.getName());
//            exerciseEntity.setTarget(muscleRepository.findByName(exercise.getTarget()));
//            exerciseEntity.setEquipment(equipmentRepository.findByName(exercise.getEquipment()));
//            exerciseEntity.setGifUrl(exercise.getGifUrl());
//            exerciseEntity.setBodyPart(bodyPartRepository.findByName(exercise.getBodyPart()));
//
//
//
//            exercisesSaved.add(exerciseRepository.save((exerciseEntity)));
//        }

        return null;


    }

//    return this.exerciseRepository.findByMuscle(muscle_id).stream().map(exerciseEntity -> {
//        return exerciseMapper.mapExercise(exerciseEntity);
//    }).collect(Collectors.toList());

    @Override
    public Set<ExerciseDTO> getExercisesByBodyPart(Long id) {
        return this.exerciseRepository.findByBodyPart(id).stream().map(exerciseEntity -> {
            return exerciseMapper.entityToDTO(exerciseEntity);
        }).collect(Collectors.toSet());
    }

    @Override
    public Set<ExerciseDTO> getExercisesByName(String name, Pageable pageable) {
        return this.exerciseRepository.findByName(name, pageable).stream().map(exerciseEntity -> {
            return exerciseMapper.entityToDTO(exerciseEntity);
        }).collect(Collectors.toSet());
    }

//    @Override
//    public ExerciseForWorkoutDTO getExerciseForWorkout(ExerciseForWorkoutEntity exerciseForWorkoutEntity) {
//        return exerciseMapper.forWorkoutEntityToDTO(exerciseForWorkoutEntity);
//    }

//    @Override
//    public List<Exercise> getExercisesByMuscle(Long muscle_id, Pageable pageable) {
//        return exerciseRepository.findByMuscle(muscle_id,pageable);
//    }


}
