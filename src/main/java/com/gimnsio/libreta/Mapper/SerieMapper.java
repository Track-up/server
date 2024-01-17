package com.gimnsio.libreta.Mapper;

import com.gimnsio.libreta.DTO.exercises.ExerciseForWorkoutDTO;
import com.gimnsio.libreta.DTO.serie.SerieForExerciseDTO;
import com.gimnsio.libreta.persistence.entities.SerieEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SerieMapper {

    public SerieForExerciseDTO entityToForExerciseDTO(SerieEntity serieEntity);

}
