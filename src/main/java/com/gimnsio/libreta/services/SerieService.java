package com.gimnsio.libreta.services;

import com.gimnsio.libreta.persistence.entities.SerieEntity;

public interface SerieService {

    public SerieEntity getSerieEntityById(long id);

    public SerieEntity createSerieEntity(SerieEntity serie);

}
