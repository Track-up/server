package com.gimnsio.libreta.services;

import com.gimnsio.libreta.persistence.entities.SerieEntity;

import java.util.List;

public interface SerieService {

    public SerieEntity getSerieEntityById(long id);

    public SerieEntity createSerieEntity(SerieEntity serie);

    public List<SerieEntity> getSeriesOfLastWorkoutFromExerciseAndUser(long userId, long exerciseId);
}
