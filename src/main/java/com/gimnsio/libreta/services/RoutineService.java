package com.gimnsio.libreta.services;

import com.gimnsio.libreta.DTO.routines.RoutineBasicsDTO;
import com.gimnsio.libreta.DTO.routines.RoutineNewDTO;
import com.gimnsio.libreta.DTO.users.UserDTO;
import com.gimnsio.libreta.DTO.routines.RoutineDTO;
import com.gimnsio.libreta.persistence.entities.RoutineEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface RoutineService {

    public List<RoutineBasicsDTO> getAllRoutines(Pageable pageable);
    public RoutineDTO getRoutineById(long id);
    public List<RoutineDTO> getRoutinesByUserCreator(UserDTO userDTO);
    public RoutineEntity createRoutine(RoutineNewDTO routineNewDTO);
    public RoutineDTO updateRoutine(long id, RoutineDTO routineDTO);
    public void deleteRoutine(long id);

    public Set<RoutineBasicsDTO> getRoutinesByUser (long user_id);


}
