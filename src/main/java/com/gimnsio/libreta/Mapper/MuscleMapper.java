package com.gimnsio.libreta.Mapper;

import com.gimnsio.libreta.DTO.muscles.MuscleToImportDTO;
import com.gimnsio.libreta.domain.Muscle;
import com.gimnsio.libreta.persistence.entities.BodyPartEntity;
import com.gimnsio.libreta.persistence.entities.MuscleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MuscleMapper {

    public Muscle mapMuscle(MuscleEntity muscleEntity);

    public MuscleEntity mapMuscleEntity(Muscle muscle);

    @Mappings({
            @Mapping(target = "bodyPart", expression = "java(new BodyPartEntity(Long.parseLong(muscleToImportDTO.getBodyPartId())))")
    })
    public MuscleEntity muscleToImportDTOToEntity(MuscleToImportDTO muscleToImportDTO);

    public Muscle mapNoThing(BodyPartEntity muscleEntity);
}
