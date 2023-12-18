package com.gimnsio.libreta.services;

import com.gimnsio.libreta.persistence.entities.MeasuresEntity;
import com.gimnsio.libreta.persistence.entities.UserEntity;
import com.gimnsio.libreta.persistence.repositories.MeasuresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MeasuresServiceImpl implements MeasuresService {

    @Autowired
    MeasuresRepository measuresRepository;

    @Override
    public MeasuresEntity getMeasuresByUser(Long id) {
        return measuresRepository.findByUser(id);
    }

    @Override
    public void createMeasures(UserEntity user) {
        MeasuresEntity measures = new MeasuresEntity();
        measures.setUser(user);
        measures.setDate(new Date());
        measuresRepository.save(measures);
    }

    @Override
    public void updateMeasures(MeasuresEntity measures) {
        measures.setDate(new Date());
        measuresRepository.save(measures);
    }

    @Override
    public void deleteMeasures(Long id) {
        measuresRepository.deleteById(id);
    }
}
