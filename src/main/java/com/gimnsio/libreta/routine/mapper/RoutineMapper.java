package com.gimnsio.libreta.routine.mapper;

import com.gimnsio.libreta.exercise.mapper.ExerciseMapper;
import com.gimnsio.libreta.routine.dto.*;
import com.gimnsio.libreta.routine.persistence.RoutineEntity;
import com.gimnsio.libreta.user.mapper.UserMapper;
import com.gimnsio.libreta.user.persistence.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.Date;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {UserMapper.class, ExerciseMapper.class}, imports = {Collectors.class, Date.class, UserEntity.class})
public interface RoutineMapper {
    @Mappings({
//            @Mapping(target = "numExercises", expression = "java(routineEntity.getExercises().size())"),
//             @Mapping(target = "exercises", expression = "java(routineEntity.getSeries().stream().map(serie -> serie.getExercise()).collect(Collectors.toList()))"),
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

    public RoutineForWorkoutDTO entityToWorkout(RoutineEntity routineEntity);//EPA EPA SIGUE AQUI


    @Mappings({
            @Mapping(target = "creator", expression = "java(new UserEntity(routineNewDTO.getCreatorId()))"),
//            @Mapping(target = "routineCoped", expression = "java(new RoutineEntity(routineNewDTO.getRoutineCopedId()))"),
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
    @Mappings({
            @Mapping(target = "creator", expression = "java(new UserEntity(routineEditDTO.getCreatorId()))"),
//            @Mapping(target = "series", expression = "java(routineEditDTO.getExercisesId().stream().map(id -> new ExerciseEntity(id)).collect(Collectors.toList()))"),
            @Mapping(target = "dateOfLastEdition", expression = "java(new Date())")
    })
    public void UpdateRoutineFromEditDTO(RoutineEditDTO routineEditDTO, @MappingTarget RoutineEntity routineEntity);

}
