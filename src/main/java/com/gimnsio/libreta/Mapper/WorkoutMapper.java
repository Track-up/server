package com.gimnsio.libreta.Mapper;

import com.gimnsio.libreta.DTO.routines.RoutineForWorkoutDTO;
import com.gimnsio.libreta.DTO.workout.WorkoutDTO;
import com.gimnsio.libreta.persistence.entities.WorkoutEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {

//    @Mappings(
//            @Mapping(target = "exercisesOfWorkout.exercise.id",expression = "routine")
//    )
    public WorkoutEntity routineEntityToWorkout(RoutineForWorkoutDTO routine);

    public WorkoutDTO entityToDTO(WorkoutEntity workoutEntity);



}
