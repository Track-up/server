package com.gimnsio.libreta.services;

import com.gimnsio.libreta.DTO.routines.RoutineBasicsDTO;
import com.gimnsio.libreta.DTO.routines.RoutineNewDTO;
import com.gimnsio.libreta.Mapper.ExerciseMapper;
import com.gimnsio.libreta.Mapper.RoutineMapper;
import com.gimnsio.libreta.DTO.routines.RoutineDTO;
import com.gimnsio.libreta.persistence.entities.RoutineEntity;
import com.gimnsio.libreta.persistence.repositories.RoutineRepository;
import com.gimnsio.libreta.DTO.users.UserDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class RoutineServiceImpl implements RoutineService {

    final private RoutineRepository routineRepository;

    final private RoutineMapper routineMapper;
    final private ExerciseMapper exerciseMapper;

    public RoutineServiceImpl (RoutineRepository routineRepository,RoutineMapper routineMapper,ExerciseMapper exerciseMapper){
        this.routineMapper=routineMapper;
        this.routineRepository=routineRepository;
        this.exerciseMapper= exerciseMapper;
    }

    @Override
    public List<RoutineBasicsDTO> getAllRoutines(Pageable pageable) {
        return routineRepository.findAll(pageable).stream().map(routineEntity -> {

            return routineMapper.entityToBasics(routineEntity);

        }).collect(Collectors.toList());
    }

    @Override
    public RoutineDTO getRoutineById(long id) {

        Optional<RoutineEntity> routineEntityOptional = routineRepository.findById(id);

        if(routineEntityOptional.isPresent()){
            return routineMapper.mapRoutine(routineEntityOptional.get());
        }else {
            throw new NoSuchElementException("No se encontró la rutina con ID: " + id);
        }
    }

    @Override
    public List<RoutineDTO> getRoutinesByUserCreator(UserDTO userDTO) {
        return null;
    }

    @Override
    public RoutineEntity createRoutine(RoutineNewDTO routineNewDTO) {
        RoutineEntity routineEntity = routineMapper.newToEntity(routineNewDTO);
        routineEntity.setDateOfCreation(new Date());
        try {
            routineRepository.save(routineEntity);
        }catch (Exception e){

        }
        return routineEntity;
    }

    @Override
    public RoutineDTO updateRoutine(long id, RoutineDTO routineDTO) {

        Optional<RoutineEntity> routineEntityOptional = routineRepository.findById(id);

        RoutineEntity routineEntity = null;
        if (routineEntityOptional.isPresent()) {
            routineEntity = routineEntityOptional.get();

            // Actualiza los ejercicios de la rutina TODO OJO AQUI
            routineEntity.setExercises(routineDTO.getExercises().stream().map(exerciseMapper::mapExerciseEntity).collect(Collectors.toList()));
            // ... Actualiza otros campos según tus necesidades ...
            routineEntity.setId(id);
            routineRepository.save(routineEntity);

        }

        return routineMapper.mapRoutine(routineEntity);
    }

    @Override
    public void deleteRoutine(long id) {
        Optional<RoutineEntity> routineEntityOptional = routineRepository.findById(id);

        if(routineEntityOptional.isPresent()){
            routineRepository.deleteById(id);//TODO FALLA org.postgresql.util.PSQLException: ERROR: update or delete on table "exercises" violates foreign key constraint "fk58bhggitkrg5uyg7s7qwtevsu" on table "routine_exercise"
        }else{
            throw new NoSuchElementException("No se encontró la rutina con ID: " + id);
        }

    }

    @Override
    public Set<RoutineDTO> getRoutinesByUser(long user_id) {
        Set<RoutineEntity> routinesEntity = routineRepository.findByUser(user_id);
        Set<RoutineDTO> routineDTOS = null;
        if (!routinesEntity.isEmpty()){
            routineDTOS = routinesEntity.stream().map(routineMapper::mapRoutine).collect(Collectors.toSet());
        }
        return routineDTOS;
    }


}
