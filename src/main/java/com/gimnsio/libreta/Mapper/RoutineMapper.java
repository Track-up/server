package com.gimnsio.libreta.Mapper;

import com.gimnsio.libreta.DTO.routines.*;
import com.gimnsio.libreta.persistence.entities.RoutineEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.Date;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {UserMapper.class,ExerciseMapper.class}, imports = {Collectors.class, Date.class})
public interface RoutineMapper {

    public RoutineDTO mapRoutine(RoutineEntity routineEntity);

    public RoutineEntity mapRoutineEntity(RoutineDTO routineDTO);

    @Mappings({
//            @Mapping(target = "numExercises", expression = "java(routineEntity.getExercises().size())"),
//            @Mapping(source = "creator.username", target = "creator"),
            //@Mapping(target = "isPublic", ignore = true) // Assuming you don't need isPublic in RoutineBasicsDTO
    })
    public RoutineBasicsDTO entityToBasics(RoutineEntity routineEntity);

    public RoutineForWorkoutDTO entityToWorkout(RoutineEntity routineEntity);


    @Mappings({
            @Mapping(target = "creator", expression = "java(new UserEntity(routineNewDTO.getCreatorId()))"),
            @Mapping(target = "exercises", expression = "java(routineNewDTO.getExercisesId().stream().map(id -> new ExerciseEntity(id)).collect(Collectors.toList()))"),
    })
    public RoutineEntity newToEntity(RoutineNewDTO routineNewDTO);

    @Mappings({
            @Mapping(target = "creator", expression = "java(new UserEntity(routineEditDTO.getCreatorId()))"),
            @Mapping(target = "exercises", expression = "java(routineEditDTO.getExercisesId().stream().map(id -> new ExerciseEntity(id)).collect(Collectors.toList()))"),
    })
    public RoutineEntity editToEntity(RoutineEditDTO routineEditDTO);
    @Mappings({
            @Mapping(target = "creator", expression = "java(new UserEntity(routineEditDTO.getCreatorId()))"),
            @Mapping(target = "exercises", expression = "java(routineEditDTO.getExercisesId().stream().map(id -> new ExerciseEntity(id)).collect(Collectors.toList()))"),
            @Mapping(target = "dateOfLastEdition", expression = "java(new Date())")
    })
    public void UpdateRoutineFromEditDTO(RoutineEditDTO routineEditDTO, @MappingTarget RoutineEntity routineEntity);


    public RoutineIdDTO entityToIdDTO(RoutineEntity routineEntity);
}
