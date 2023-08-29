package com.gimnsio.libreta.services;

import com.gimnsio.libreta.domain.Routine;
import com.gimnsio.libreta.DTO.users.UserDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface RoutineService {

    public List<Routine> getAllRoutines(Pageable pageable);
    public Routine getRoutineById(long id);
    public List<Routine> getRoutinesByUserCreator(UserDTO userDTO);
    public Routine createRoutine(Routine routine);
    public Routine updateRoutine(long id, Routine routine);
    public void deleteRoutine(long id);

    public Set<Routine> getRoutinesByUser (long user_id);
}
