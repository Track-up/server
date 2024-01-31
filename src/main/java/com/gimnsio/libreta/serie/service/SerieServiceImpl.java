package com.gimnsio.libreta.serie.service;

import com.gimnsio.libreta.exercise.dto.ExerciseForWorkoutDTO;
import com.gimnsio.libreta.serie.dto.SerieForExerciseDTO;
import com.gimnsio.libreta.serie.mapper.SerieMapper;
import com.gimnsio.libreta.exception.ApiRequestException;
import com.gimnsio.libreta.exercise.persistence.ExerciseEntity;
import com.gimnsio.libreta.serie.persistence.SerieEntity;
import com.gimnsio.libreta.workout.persistence.WorkoutEntity;
import com.gimnsio.libreta.serie.persistence.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SerieServiceImpl implements SerieService {

    @Autowired
    SerieRepository serieRepository;

    @Autowired
    SerieMapper serieMapper;

    @Override
    public SerieEntity getSerieEntityById(long id) {
        Optional<SerieEntity> serieEntityOptional = serieRepository.findById(id);
        if (serieEntityOptional.isPresent()) {
            return serieEntityOptional.get();
        } else {
            throw new ApiRequestException("No se encontró la serie con el ID: " + id);
        }
    }

    @Override
    public SerieForExerciseDTO saveSerie(SerieEntity serie) {
        return serieMapper.entityToForExerciseDTO(serieRepository.save(serie));
    }

    @Override
    public SerieForExerciseDTO createSerie(ExerciseEntity exercise, long difficulty, SerieEntity serie, WorkoutEntity workout) {
        if (difficulty == 2) {
            if (serie.getReps() > 12) {
                serie.setReps(serie.getReps() + 1);
            } else {
                serie.setReps(6L);
                serie.setKg(serie.getKg() + 1);
            }
        } else if (difficulty == 3) {
            if (serie.getReps() > 12) {
                serie.setReps(serie.getReps() + 2);
            } else {
                serie.setReps(6L);
                serie.setKg(serie.getKg() + 2);
            }
        }
        return saveSerie(new SerieEntity(exercise, serie.getReps(), serie.getKg(), workout));
    }


    @Override
    public List<SerieEntity> getSeriesOfLastWorkoutFromExerciseAndUser(String exerciseId, long userId) {
        return serieRepository.findLastByExerciseAndUser(exerciseId, userId);
    }

    @Override
    public void saveAll(List<SerieEntity> seriesEntity) {
        serieRepository.saveAll(seriesEntity);
    }

    @Override
    public void deleteSerie(SerieEntity serieEntity) {
        serieRepository.delete(serieEntity);
    }

    @Override
    public List<SerieEntity> getSeriesOfWorkout(WorkoutEntity workoutEntity) {
        return serieRepository.findByWorkout(workoutEntity);
    }

    @Override
    public List<ExerciseForWorkoutDTO> getSeriesOfExerciseForWorkout(WorkoutEntity workoutEntity) {
        List<SerieEntity> series = getSeriesOfWorkout(workoutEntity);
        List<ExerciseForWorkoutDTO> exercises = new ArrayList<>();
        for (SerieEntity serie : series) {
            if (!exercises.isEmpty() && serie.getExercise().getId() == exercises.get(exercises.size() - 1).getExerciseId()) {
                exercises.get(exercises.size() - 1).getSeries().add(serieMapper.entityToForExerciseDTO(serie));
            } else {
                ExerciseForWorkoutDTO exerciseForWorkoutDTO = new ExerciseForWorkoutDTO();
                exerciseForWorkoutDTO.setExerciseId(serie.getExercise().getId());
                exerciseForWorkoutDTO.setName(serie.getExercise().getName());
                exerciseForWorkoutDTO.setImages(serie.getExercise().getImages());
                exerciseForWorkoutDTO.setSeries(new ArrayList<>());
                exerciseForWorkoutDTO.getSeries().add(serieMapper.entityToForExerciseDTO(serie));
                exercises.add(exerciseForWorkoutDTO);
            }
        }
        return exercises;
    }



    }
