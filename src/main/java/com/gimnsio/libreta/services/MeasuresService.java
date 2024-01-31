package com.gimnsio.libreta.services;

import com.gimnsio.libreta.persistence.entities.MeasuresEntity;
import com.gimnsio.libreta.user.persistence.UserEntity;

public interface MeasuresService {

    MeasuresEntity getMeasuresByUser(Long id);

    void createMeasures(UserEntity user);

    void updateMeasures(MeasuresEntity measures);

    void deleteMeasures(Long id);


}
