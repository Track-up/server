package com.gimnsio.libreta.Mapper;

import com.gimnsio.libreta.DTO.routines.RoutineBasicsDTO;
import com.gimnsio.libreta.DTO.routines.RoutineNewDTO;
import com.gimnsio.libreta.DTO.routines.RoutineDTO;
import com.gimnsio.libreta.persistence.entities.RoutineEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {UserMapper.class,ExerciseMapper.class})
public interface RoutineMapper {

    public RoutineDTO mapRoutine(RoutineEntity routineEntity);

    public RoutineEntity mapRoutineEntity(RoutineDTO routineDTO);

    @Mappings({
            @Mapping(target = "numExercises", expression = "java(routineEntity.getExercises().size())"),
            @Mapping(source = "creator.username", target = "creator"),
            //@Mapping(target = "isPublic", ignore = true) // Assuming you don't need isPublic in RoutineBasicsDTO
    })
    public RoutineBasicsDTO entityToBasics(RoutineEntity routineEntity);

    public RoutineEntity newToEntity(RoutineNewDTO routineNewDTO);
}
