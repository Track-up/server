package com.gimnsio.libreta.serie.service;

import com.gimnsio.libreta.exercise.dto.ExerciseForWorkoutDTO;
import com.gimnsio.libreta.exercise.persistence.ExerciseEntity;
import com.gimnsio.libreta.serie.dto.SerieForExerciseDTO;
import com.gimnsio.libreta.serie.persistence.SerieEntity;
import com.gimnsio.libreta.workout.persistence.WorkoutEntity;

import java.util.List;

public interface SerieService {

    public SerieEntity getSerieEntityById(long id);

    public SerieForExerciseDTO saveSerie(SerieEntity serie);

    public SerieForExerciseDTO createSerie(ExerciseEntity exercise, long difficulty, SerieEntity serie, WorkoutEntity workout);

    public List<SerieEntity> getSeriesOfLastWorkoutFromExerciseAndUser(String exerciseId, long userId);

    void saveAll(List<SerieEntity> seriesEntity);

    void deleteSerie(SerieEntity serieEntity);

    List<SerieEntity> getSeriesOfWorkout(WorkoutEntity workoutEntity);

    List<ExerciseForWorkoutDTO> getSeriesOfExerciseForWorkout(WorkoutEntity workoutEntity);

}
