package com.gimnsio.libreta.exercise.mapper;

import com.gimnsio.libreta.Mapper.BodyPartMapper;
import com.gimnsio.libreta.Mapper.EquipmentMapper;
import com.gimnsio.libreta.exercise.dto.ExerciseDTO;
import com.gimnsio.libreta.exercise.dto.ExerciseForRoutineDTO;
import com.gimnsio.libreta.exercise.dto.ExerciseMinimalDTO;
import com.gimnsio.libreta.exercise.persistence.ExerciseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { EquipmentMapper.class, BodyPartMapper.class})
public interface ExerciseMapper {



    public ExerciseEntity exerciseMinimalDTOListToExerciseEntitySet(ExerciseMinimalDTO exerciseMinimalDTO);

    public ExerciseMinimalDTO entityToMinimalDTO(ExerciseEntity exerciseEntity);

    public ExerciseForRoutineDTO entityToForRoutine(ExerciseEntity exerciseEntity);

    public ExerciseDTO entityToDTO(ExerciseEntity exerciseEntity);

    public ExerciseForRoutineDTO seriesToForRoutine(ExerciseEntity exerciseEntity);

//    ExerciseEntity newToEntity(ExerciseNewDTO exerciseNewDTO);
//    @Mappings(value = {
//            @Mapping(target = "name", expression = "java(exerciseForWorkoutEntity.getExercise().getName())"),
//            @Mapping(target = "image", expression = "java(exerciseForWorkoutEntity.getExercise().getGifUrl())"),
//    })
//    public ExerciseForWorkoutDTO forWorkoutEntityToDTO(ExerciseForWorkoutEntity exerciseForWorkoutEntity);


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
