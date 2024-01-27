package com.gimnsio.libreta.serie.mapper;

import com.gimnsio.libreta.serie.dto.SerieForExerciseDTO;
import com.gimnsio.libreta.serie.persistence.SerieEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SerieMapper {

    public SerieForExerciseDTO entityToForExerciseDTO(SerieEntity serieEntity);


}
