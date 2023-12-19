package com.gimnsio.libreta.services;

import com.gimnsio.libreta.DTO.exercises.ExerciseForRoutineDTO;
import com.gimnsio.libreta.persistence.entities.ExerciseEntity;
import com.gimnsio.libreta.persistence.entities.RoutineEntity;
import com.gimnsio.libreta.persistence.entities.SerieExampleEntity;
import com.gimnsio.libreta.persistence.repositories.SerieExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SerieExampleServiceImpl implements SerieExampleService{

    @Autowired
    private SerieExampleRepository serieExampleRepository;

    @Override
    public List<SerieExampleEntity> getSeriesOfRoutine(RoutineEntity routine) {
        return serieExampleRepository.findByRoutine(routine);
    }

    @Override
    public SerieExampleEntity save(SerieExampleEntity serieExampleEntity) {
        return serieExampleRepository.save(serieExampleEntity);
    }

    @Override
    public void delete(SerieExampleEntity serieExampleEntity) {
        serieExampleRepository.delete(serieExampleEntity);
    }

    @Override
    public List<SerieExampleEntity> findByRoutineAndExercise(RoutineEntity routine, ExerciseEntity exercise) {
        return serieExampleRepository.findByRoutineAndExercise(routine, exercise);
    }
}
