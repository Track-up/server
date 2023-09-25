package com.gimnsio.libreta.services;

import com.gimnsio.libreta.DTO.routines.*;
import com.gimnsio.libreta.DTO.users.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoutineService {

    public List<RoutineBasicsDTO> getAllRoutines(Pageable pageable);
    public RoutineDTO getRoutineById(long id);
    public List<RoutineDTO> getRoutinesByUserCreator(UserDTO userDTO);
    public RoutineIdDTO createRoutine(RoutineNewDTO routineNewDTO);
    public RoutineDTO updateRoutine(RoutineEditDTO routineEditDTO);
    public void deleteRoutine(long id);

    public Page<RoutineBasicsDTO> getRoutinesByUser (long user_id, Pageable pageable);
    public RoutineForWorkoutDTO getRoutineForWorkout(long id);


}
