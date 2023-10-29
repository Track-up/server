package com.gimnsio.libreta.services;

import com.gimnsio.libreta.DTO.serie.SerieForExerciseDTO;
import com.gimnsio.libreta.persistence.entities.SerieEntity;

import java.util.List;

public interface SerieService {

    public SerieEntity getSerieEntityById(long id);

    public SerieForExerciseDTO saveSerie(SerieEntity serie);

    public List<SerieEntity> getSeriesOfLastWorkoutFromExerciseAndUser(long exerciseId, long userId);

//    public List<SerieEntity> getSeriesOfExerciseForWorkout(ExerciseForWorkoutEntity exerciseForWorkout);
}
