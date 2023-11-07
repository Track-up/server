package com.gimnsio.libreta.Mapper;

import com.gimnsio.libreta.DTO.routines.RoutineBasicsDTO;
import com.gimnsio.libreta.DTO.routines.RoutineDTO;
import com.gimnsio.libreta.DTO.routines.RoutineIdDTO;
import com.gimnsio.libreta.DTO.routines.RoutineNewDTO;
import com.gimnsio.libreta.persistence.entities.RoutineEntity;
import com.gimnsio.libreta.persistence.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Date;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {UserMapper.class,ExerciseMapper.class}, imports = {Collectors.class, Date.class, UserEntity.class})
public interface RoutineMapper {
    @Mappings({
//            @Mapping(target = "numExercises", expression = "java(routineEntity.getExercises().size())"),
//            @Mapping(source = "creator.username", target = "creator"),
            //@Mapping(target = "isPublic", ignore = true) // Assuming you don't need isPublic in RoutineBasicsDTO
    })
    public RoutineDTO entityToDTO(RoutineEntity routineEntity);

//    public RoutineEntity mapRoutineEntity(RoutineDTO routineDTO);
//
//    @Mappings({
////            @Mapping(target = "numExercises", expression = "java(routineEntity.getExercises().size())"),
////            @Mapping(source = "creator.username", target = "creator"),
//            //@Mapping(target = "isPublic", ignore = true) // Assuming you don't need isPublic in RoutineBasicsDTO
//    })
    public RoutineBasicsDTO entityToBasics(RoutineEntity routineEntity);
//
//    public RoutineForWorkoutDTO entityToWorkout(RoutineEntity routineEntity);
//
//
    @Mappings({
            @Mapping(target = "creator", expression = "java(new UserEntity(routineNewDTO.getCreatorId()))"),
            @Mapping(target = "dateOfCreation", expression = "java(new Date())"),
            @Mapping(target = "dateOfLastEdition", expression = "java(new Date())")
//            @Mapping(target = "series", expression = "java(routineNewDTO.exercises.stream().map(id -> new ExerciseEntity(id)).collect(Collectors.toList()))"),
    })
    public RoutineEntity newToEntity(RoutineNewDTO routineNewDTO);
//
//    @Mappings({
//            @Mapping(target = "creator", expression = "java(new UserEntity(routineEditDTO.getCreatorId()))"),
//            @Mapping(target = "series", expression = "java(routineEditDTO.getExercisesId().stream().map(id -> new ExerciseEntity(id)).collect(Collectors.toList()))"),
//    })
//    public RoutineEntity editToEntity(RoutineEditDTO routineEditDTO);
//    @Mappings({
//            @Mapping(target = "creator", expression = "java(new UserEntity(routineEditDTO.getCreatorId()))"),
//            @Mapping(target = "series", expression = "java(routineEditDTO.getExercisesId().stream().map(id -> new ExerciseEntity(id)).collect(Collectors.toList()))"),
//            @Mapping(target = "dateOfLastEdition", expression = "java(new Date())")
//    })
//    public void UpdateRoutineFromEditDTO(RoutineEditDTO routineEditDTO, @MappingTarget RoutineEntity routineEntity);
//
//
    public RoutineIdDTO entityToIdDTO(RoutineEntity routineEntity);
}
