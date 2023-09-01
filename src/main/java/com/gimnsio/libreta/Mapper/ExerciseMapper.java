package com.gimnsio.libreta.Mapper;

import com.gimnsio.libreta.DTO.exercises.ExerciseDTO;
import com.gimnsio.libreta.DTO.exercises.ExerciseForRoutineDTO;
import com.gimnsio.libreta.DTO.exercises.ExerciseMinimalDTO;
import com.gimnsio.libreta.domain.Exercise;
import com.gimnsio.libreta.persistence.entities.ExerciseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {

    public Exercise mapExercise(ExerciseEntity exerciseEntity);

    public ExerciseEntity mapExerciseEntity(Exercise exercise);

    public ExerciseEntity exerciseMinimalDTOListToExerciseEntitySet(ExerciseMinimalDTO exerciseMinimalDTO);

    public ExerciseMinimalDTO entityToMinimalDTO(ExerciseEntity exerciseEntity);

    public ExerciseForRoutineDTO entityToForRoutine(ExerciseEntity exerciseEntity);
    @Mappings({
            @Mapping(target = "bodyPart", expression = "java(exerciseEntity.getBodyPart() != null ? exerciseEntity.getBodyPart().getName() : null)"),
            @Mapping(target = "target", expression = "java(exerciseEntity.getTarget() != null ? exerciseEntity.getTarget().getName() : null)"),
            @Mapping(target = "equipment", expression = "java(exerciseEntity.getEquipment() != null ? exerciseEntity.getEquipment().getName() : null)"),
    })
    public ExerciseDTO entityToDTO(ExerciseEntity exerciseEntity);



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
