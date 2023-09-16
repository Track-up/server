package com.gimnsio.libreta.services;

import com.gimnsio.libreta.exception.ApiRequestException;
import com.gimnsio.libreta.persistence.entities.SerieEntity;
import com.gimnsio.libreta.persistence.repositories.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SerieServiceImpl implements SerieService{

    @Autowired
    SerieRepository serieRepository;


    @Override
    public SerieEntity getSerieEntityById(long id) {
        Optional<SerieEntity> serieEntityOptional = serieRepository.findById(id);
        if(serieEntityOptional.isPresent()){
            return serieEntityOptional.get();
        }else {
            throw new ApiRequestException("No se encontró la serie con el ID: " + id);
        }
    }

    @Override
    public SerieEntity createSerieEntity(SerieEntity serie) {
        return serieRepository.save(serie);
    }
}