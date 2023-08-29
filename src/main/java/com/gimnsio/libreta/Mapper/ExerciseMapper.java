package com.gimnsio.libreta.Mapper;

import com.gimnsio.libreta.domain.Exercise;
import com.gimnsio.libreta.persistence.entities.ExerciseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {

    public Exercise mapExercise(ExerciseEntity exerciseEntity);

    public ExerciseEntity mapExerciseEntity(Exercise exercise);
//    @Mappings(value = {
//            @Mapping(target = "id", expression = "java(Long.parseLong(exercisesToImportDTO.getId()))"),
//            @Mapping(target = "bodyPart", source = "java(mapStringToBodyPartEntity(exercisesToImportDTO.bodyPart))"),
//            @Mapping(target = "equipment", source = "exercisesToImportDTO.equipment"),
//            @Mapping(target = "target", source = "exercisesToImportDTO.target")
//    })
//    public ExerciseEntity importToEntity(ExerciseToImportDTO exerciseToImportDTO);
//
//    default BodyPartEntity mapStringToBodyPartEntity(String bodyPartName,@Context BodyPartRepository bodyPartRepository) {
//        if (bodyPartName == null) {
//            return null;
//        }
//        return bodyPartRepository.findByName(bodyPartName);
//    }
}
