package com.gimnsio.libreta.workout.mapper;

import com.gimnsio.libreta.routine.dto.RoutineForWorkoutDTO;
import com.gimnsio.libreta.workout.dto.WorkoutDTO;
import com.gimnsio.libreta.workout.persistence.WorkoutEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {

//    @Mappings(
//            @Mapping(target = "exercisesOfWorkout.exercise.id",expression = "routine")
//    )
    public WorkoutEntity routineEntityToWorkout(RoutineForWorkoutDTO routine);

    public WorkoutDTO entityToDTO(WorkoutEntity workoutEntity);





}
