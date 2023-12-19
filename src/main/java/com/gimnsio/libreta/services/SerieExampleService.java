package com.gimnsio.libreta.services;

import com.gimnsio.libreta.DTO.exercises.ExerciseForRoutineDTO;
import com.gimnsio.libreta.persistence.entities.ExerciseEntity;
import com.gimnsio.libreta.persistence.entities.RoutineEntity;
import com.gimnsio.libreta.persistence.entities.SerieExampleEntity;

import java.util.List;

public interface SerieExampleService {

    List<SerieExampleEntity> getSeriesOfRoutine(RoutineEntity routine);

    SerieExampleEntity save(SerieExampleEntity serieExampleEntity);

    void delete(SerieExampleEntity serieExampleEntity);

    List<SerieExampleEntity>findByRoutineAndExercise(RoutineEntity routine, ExerciseEntity exercise);

}
