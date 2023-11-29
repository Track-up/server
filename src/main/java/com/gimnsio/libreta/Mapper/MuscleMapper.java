package com.gimnsio.libreta.Mapper;

import com.gimnsio.libreta.DTO.muscles.MusclePercentDTO;
import com.gimnsio.libreta.DTO.muscles.MuscleToImportDTO;
import com.gimnsio.libreta.DTO.muscles.MuscleForExerciseDTO;
import com.gimnsio.libreta.domain.Muscle;
import com.gimnsio.libreta.persistence.entities.BodyPartEntity;
import com.gimnsio.libreta.persistence.entities.MuscleEntity;
import com.gimnsio.libreta.persistence.entities.MuscleForExerciseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MuscleMapper {

    public Muscle mapMuscle(MuscleEntity muscleEntity);

    public MuscleEntity mapMuscleEntity(Muscle muscle);

    @Mappings({
            @Mapping(target = "bodyPart", expression = "java(new BodyPartEntity(Long.parseLong(muscleToImportDTO.getBodyPartId())))")
    })
    public MuscleEntity muscleToImportDTOToEntity(MuscleToImportDTO muscleToImportDTO);

    public MuscleForExerciseDTO entityToForExerciseDTO(MuscleEntity muscleEntity);

    public Muscle mapNoThing(BodyPartEntity muscleEntity);

    List<MusclePercentDTO> musclePercentEntityToDTO(List<MuscleForExerciseEntity> muscleForExerciseEntities);
    @Mappings({
            @Mapping(target = "name", source = "muscleForExerciseEntity.muscle.name")
    })
    MusclePercentDTO muscleForExerciseEntityToMusclePercentDTO(MuscleForExerciseEntity muscleForExerciseEntity);
}
