package com.gimnsio.libreta.Mapper;

import com.gimnsio.libreta.DTO.bodyPart.BodyPartForExerciseDTO;
import com.gimnsio.libreta.persistence.entities.BodyPartEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BodyPartMapper {

    public BodyPartForExerciseDTO entityToForExerciseDTO(BodyPartEntity bodyPartEntity);

}
