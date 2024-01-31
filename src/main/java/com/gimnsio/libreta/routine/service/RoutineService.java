package com.gimnsio.libreta.routine.service;

import com.gimnsio.libreta.user.dto.UserDTO;
import com.gimnsio.libreta.routine.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoutineService {

    public List<RoutineDTO> getAllRoutines(Pageable pageable);
    public RoutineDTO getRoutineById(long id);
    public List<RoutineDTO> getRoutinesByUserCreator(UserDTO userDTO);
    public Long createRoutine(RoutineNewDTO routineNewDTO);
    public RoutineDTO updateRoutine(Long id, RoutineEditDTO routineEditDTO);
    public void deleteRoutine(long id);

    public List<RoutineBasicsDTO> getRoutinesByUser (long user_id, Pageable pageable);
    public List<RoutineBasicsDTO> getRoutinesByUsername (String username, Pageable pageable);
    public List<RoutineDTO> getRoutinesByName (String name, Pageable pageable);
//    public List<List<RoutineBasicsDTO>> getRoutinesByString (String name, Pageable pageable);
    public RoutineForWorkoutDTO getRoutineForWorkout(long id);

    RoutineDTO getRoutineByWorkout(Long id);

}
