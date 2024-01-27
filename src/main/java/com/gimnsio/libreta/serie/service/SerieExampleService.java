package com.gimnsio.libreta.serie.service;

import com.gimnsio.libreta.persistence.entities.ExerciseEntity;
import com.gimnsio.libreta.routine.persistence.RoutineEntity;
import com.gimnsio.libreta.serie.persistence.SerieExampleEntity;

import java.util.List;

public interface SerieExampleService {

    List<SerieExampleEntity> getSeriesOfRoutine(RoutineEntity routine);

    SerieExampleEntity save(SerieExampleEntity serieExampleEntity);

    void delete(SerieExampleEntity serieExampleEntity);

    List<SerieExampleEntity>findByRoutineAndExercise(RoutineEntity routine, ExerciseEntity exercise);

}
