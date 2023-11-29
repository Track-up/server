package com.gimnsio.libreta.services;

import com.gimnsio.libreta.DTO.serie.SerieForExerciseDTO;
import com.gimnsio.libreta.persistence.entities.ExerciseEntity;
import com.gimnsio.libreta.persistence.entities.SerieEntity;
import com.gimnsio.libreta.persistence.entities.WorkoutEntity;

import java.util.List;

public interface SerieService {

    public SerieEntity getSerieEntityById(long id);

    public SerieForExerciseDTO saveSerie(SerieEntity serie);

    public SerieForExerciseDTO createSerie(ExerciseEntity exercise, long difficulty, SerieEntity serie, WorkoutEntity workout);

    public List<SerieEntity> getSeriesOfLastWorkoutFromExerciseAndUser(long exerciseId, long userId);

    void saveAll(List<SerieEntity> seriesEntity);

    void deleteSerie(SerieEntity serieEntity);

//    public List<SerieEntity> getSeriesOfExerciseForWorkout(ExerciseForWorkoutEntity exerciseForWorkout);
}
