package com.gimnsio.libreta.Mapper;

import com.gimnsio.libreta.DTO.bodyPart.BodyPartForExerciseDTO;
import com.gimnsio.libreta.persistence.entities.BodyPartEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BodyPartMapper {

    BodyPartForExerciseDTO entityToForExerciseDTO(BodyPartEntity bodyPartEntity);

    List<BodyPartForExerciseDTO> map(List<BodyPartEntity> bodyPartEntities);
}
