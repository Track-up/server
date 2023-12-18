package com.gimnsio.libreta.services;

import com.gimnsio.libreta.DTO.exercises.ExerciseDTO;
import com.gimnsio.libreta.DTO.exercises.ExerciseNewDTO;
import com.gimnsio.libreta.DTO.muscles.MusclePercentIdDTO;
import com.gimnsio.libreta.Mapper.ExerciseMapper;
import com.gimnsio.libreta.persistence.entities.ExerciseEntity;
import com.gimnsio.libreta.persistence.entities.MuscleEntity;
import com.gimnsio.libreta.persistence.enums.*;
import com.gimnsio.libreta.persistence.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
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
    MuscleForExerciseRepository muscleForExerciseRepository;
    @Autowired
    private MessageSource messageSource;


    //AQUÍ ACABA

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ExerciseMapper exerciseMapper) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseMapper = exerciseMapper;
    }

    @Override
    public List<ExerciseEntity> getAllExercises(Pageable pageable, Locale locale) {
        List<ExerciseEntity>exercises =  this.exerciseRepository.findAll(pageable).stream().collect(Collectors.toList());
        if (!locale.getLanguage().equals("en")) {
            translateExercises(locale, exercises);
        }
        return exercises;
    }



    @Override
    public ExerciseEntity getExerciseById(Long id) {
        Optional<ExerciseEntity> exerciseEntityOptional = exerciseRepository.findById(null);

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


//    @Override
//    public Exercise updateExercise(Long id, Exercise exercise) {
//        Optional<ExerciseEntity> exerciseEntityOptional = exerciseRepository.findById(null);
//
//        if (exerciseEntityOptional.isPresent()) {
//            ExerciseEntity exerciseEntity = exerciseMapper.mapExerciseEntity(exercise);
////            exerciseEntity.setId(id);
//            return exerciseMapper.mapExercise(exerciseRepository.save(exerciseEntity));
//        } else {
//            throw new NoSuchElementException("No se encontró el ejercicio con el ID: " + id);
//        }
//    }

    @Override
    public ExerciseEntity createExercise(ExerciseNewDTO exercise) {

//        ExerciseEntity exerciseEntity = exerciseRepository.save(exerciseMapper.newToEntity(exercise));
        for (MusclePercentIdDTO muscle : exercise.getMuscles()) {
            MuscleEntity muscleEntity = new MuscleEntity();
            muscle.setId(muscle.getId());
//            MuscleForExerciseEntity muscleForExerciseEntity = new MuscleForExerciseEntity(exerciseEntity,muscleEntity, muscle.getPercent() );
        }

        return null;
    }

    @Override
    public void deleteExercise(Long id) {
        Optional<ExerciseEntity> optionalExerciseEntity = exerciseRepository.findById(null);
        if (optionalExerciseEntity.isPresent()) {
            exerciseRepository.delete(optionalExerciseEntity.get());
        } else {
            throw new NoSuchElementException("No se encontró el ejercicio con el ID: " + id);
        }
    }



    public Set<ExerciseEntity> createExercises(Set<ExerciseEntity> exercises) {
        for (ExerciseEntity exercise: exercises) {
            exercise.setInstructions(null);
            exerciseRepository.save(exercise);
        }
        return exercises;

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
    public List<ExerciseEntity> getExercisesByName(String name, Pageable pageable, Locale locale) {

        List<ExerciseEntity> exercises = this.exerciseRepository.findByName(name, pageable).stream().collect(Collectors.toList());
        if (!locale.getLanguage().equals("en")) {
            translateExercises(locale, exercises);
        }
        return exercises;

        //        return this.exerciseRepository.findByName(name, pageable).stream().map(exerciseEntity -> {
//            return exerciseMapper.entityToDTO(exerciseEntity);
//        }).collect(Collectors.toSet());
    }

    @Override
    public List<ExerciseEntity> getExercisesByForce(String force, Pageable pageable, Locale userLocale) {
        List<ExerciseEntity> exercises = this.exerciseRepository.findByForce(Force.valueOf(force).ordinal(), pageable);
        if (!userLocale.getLanguage().equals("en")) {
            translateExercises(userLocale, exercises);
        }
        return exercises;
    }

    @Override
    public List<ExerciseEntity> getExercisesByLevel(String level, Pageable pageable, Locale userLocale) {
        List<ExerciseEntity> exercises = this.exerciseRepository.findByLevel(Level.valueOf(level).ordinal(), pageable);
        if (!userLocale.getLanguage().equals("en")) {
            translateExercises(userLocale, exercises);
        }
        return exercises;
    }

    @Override
    public List<ExerciseEntity> getExercisesByMechanic(String mechanic, Pageable pageable, Locale userLocale) {
        List<ExerciseEntity> exercises = this.exerciseRepository.findByMechanic(Mechanic.valueOf(mechanic).ordinal(), pageable);
        if (!userLocale.getLanguage().equals("en")) {
            translateExercises(userLocale, exercises);
        }
        return exercises;
    }

    @Override
    public List<ExerciseEntity> getExercisesByEquipment(String equipment, Pageable pageable, Locale userLocale) {
        List<ExerciseEntity> exercises = this.exerciseRepository.findByEquipment(Equipment.valueOf(equipment).ordinal(), pageable);
        if (!userLocale.getLanguage().equals("en")) {
            translateExercises(userLocale, exercises);
        }
        return exercises;
    }

    @Override
    public List<ExerciseEntity> getExercisesByCategory(String category, Pageable pageable, Locale userLocale) {
        List<ExerciseEntity> exercises = this.exerciseRepository.findByCategory(Category.valueOf(category).ordinal(), pageable);
        if (!userLocale.getLanguage().equals("en")) {
            translateExercises(userLocale, exercises);
        }
        return exercises;
    }

    @Override
    public List<ExerciseEntity> getExercisesByMuscle(String muscle, Pageable pageable, Locale userLocale) {

        List<ExerciseEntity> exercises = this.exerciseRepository.findByMuscle(Muscle.valueOf(muscle).ordinal(), pageable);
        if (!userLocale.getLanguage().equals("en")) {
            translateExercises(userLocale, exercises);
        }
        return exercises;
    }

    private void translateExercises(Locale locale, List<ExerciseEntity> exercises) {
        for (ExerciseEntity exercise: exercises) {
            exercise.setName(messageSource.getMessage("exercise.name.".concat(exercise.getId()),null, locale));
        }
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
