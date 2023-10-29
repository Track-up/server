package com.gimnsio.libreta.services;

import com.gimnsio.libreta.DTO.serie.SerieForExerciseDTO;
import com.gimnsio.libreta.Mapper.SerieMapper;
import com.gimnsio.libreta.exception.ApiRequestException;
import com.gimnsio.libreta.persistence.entities.SerieEntity;
import com.gimnsio.libreta.persistence.repositories.SerieRepository;
import com.gimnsio.libreta.persistence.repositories.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerieServiceImpl implements SerieService{

    @Autowired
    SerieRepository serieRepository;

    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    SerieMapper serieMapper;

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
    public SerieForExerciseDTO saveSerie(SerieEntity serie) {
        return serieMapper.entityToForExerciseDTO(serieRepository.save(serie));
    }

    @Override
    public List<SerieEntity> getSeriesOfLastWorkoutFromExerciseAndUser(long exerciseId, long userId) {
        return serieRepository.findLastByExerciseAndUser(exerciseId,userId);
    }

//    @Override
//    public List<SerieEntity> getSeriesOfExerciseForWorkout(ExerciseForWorkoutEntity exerciseForWorkout) {
//        return serieRepository.findByExerciseForWorkout(exerciseForWorkout);
//    }


}
