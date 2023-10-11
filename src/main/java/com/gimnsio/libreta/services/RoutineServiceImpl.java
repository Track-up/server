package com.gimnsio.libreta.services;

import com.gimnsio.libreta.DTO.routines.*;
import com.gimnsio.libreta.DTO.users.UserDTO;
import com.gimnsio.libreta.Mapper.ExerciseMapper;
import com.gimnsio.libreta.Mapper.RoutineMapper;
import com.gimnsio.libreta.persistence.entities.RoutineEntity;
import com.gimnsio.libreta.persistence.repositories.RoutineRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoutineServiceImpl implements RoutineService {

    final private RoutineRepository routineRepository;

    final private RoutineMapper routineMapper;

    final private ExerciseMapper exerciseMapper;

    final private ExerciseService exerciseService;

    final private UserService userService;

    public RoutineServiceImpl(RoutineRepository routineRepository, RoutineMapper routineMapper, ExerciseMapper exerciseMapper, ExerciseService exerciseService, UserService userService) {
        this.routineMapper = routineMapper;
        this.routineRepository = routineRepository;
        this.exerciseMapper = exerciseMapper;
        this.exerciseService = exerciseService;
        this.userService = userService;
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

        if (routineEntityOptional.isPresent()) {
            return routineMapper.mapRoutine(routineEntityOptional.get());
        } else {
            throw new NoSuchElementException("No se encontró la rutina con ID: " + id);
        }
    }

    @Override
    public List<RoutineDTO> getRoutinesByUserCreator(UserDTO userDTO) {
        return null;
    }

    @Override
    public RoutineIdDTO createRoutine(RoutineNewDTO routineNewDTO) {
        checkUser(routineNewDTO.getCreatorId());
        if (routineNewDTO.getImage().isEmpty()) {
            routineNewDTO.setImage(exerciseService.getExerciseById(routineNewDTO.getExercisesId().get(0)).getGifUrl());//para un futuro en el mapper directamente
        }
        RoutineEntity routineEntity = routineMapper.newToEntity(routineNewDTO);
        routineEntity.setDateOfCreation(new Date());
        routineEntity.setDateOfLastEdition(new Date());
        ;

        return routineMapper.entityToIdDTO(routineRepository.save(routineEntity));
    }

    @Override
    public RoutineDTO updateRoutine(RoutineEditDTO routineEditDTO) {

        checkRoutine(routineEditDTO.getId());
        checkUser(routineEditDTO.getCreatorId());
        Optional<RoutineEntity> routineEntityOptional = routineRepository.findById(routineEditDTO.getId());
        if (routineEntityOptional.isEmpty()) {
            throw new NoSuchElementException("No se encontró la rutina con ID: " + routineEditDTO.getId());
        }
        RoutineEntity routineEntity = routineEntityOptional.get();
        routineMapper.UpdateRoutineFromEditDTO(routineEditDTO, routineEntity);
//        RoutineEntity routineEntity = routineMapper.editToEntity(routineEditDTO);
//        routineEntity.setDateOfCreation(routineEntityOptional.get().getDateOfCreation());

        return routineMapper.mapRoutine(routineRepository.save(routineEntity));
    }

    private void checkUser(Long id) {
        userService.getUserEntityById(id);
    }

    private void checkRoutine(long id) {

    }

    @Override
    public void deleteRoutine(long id) {
        Optional<RoutineEntity> routineEntityOptional = routineRepository.findById(id);

        if (routineEntityOptional.isPresent()) {
            routineRepository.deleteById(id);//TODO FALLA org.postgresql.util.PSQLException: ERROR: update or delete on table "exercises" violates foreign key constraint "fk58bhggitkrg5uyg7s7qwtevsu" on table "routine_exercise"
        } else {
            throw new NoSuchElementException("No se encontró la rutina con ID: " + id);
        }

    }

    @Override
    public List<RoutineBasicsDTO> getRoutinesByUser(long user_id, Pageable pageable) {

        return routineRepository.findByUser(user_id, pageable)
                .stream()
                .map(routineMapper::entityToBasics)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoutineBasicsDTO> getRoutinesByUsername(String username, Pageable pageable) {
        return routineRepository.findByUsername(username, pageable)
                .stream()
                .map(routineMapper::entityToBasics)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoutineBasicsDTO> getRoutinesByName(String name, Pageable pageable) {

        return routineRepository.findByName(name, pageable)
                .stream()
                .map(routineMapper::entityToBasics)
                .collect(Collectors.toList());
    }

    @Override
    public List<List<RoutineBasicsDTO>> getRoutinesByString(String name, Pageable pageable) {
        List<List<RoutineBasicsDTO>> pageList = new ArrayList<>();
        pageList.add(getRoutinesByName(name, pageable));
        pageList.add(getRoutinesByUsername(name, pageable));
        return pageList;

    }

    @Override
    public RoutineForWorkoutDTO getRoutineForWorkout(long id) {
        Optional<RoutineEntity> routineEntityOptional = routineRepository.findById(id);

        if (routineEntityOptional.isPresent()) {
            return routineMapper.entityToWorkout(routineEntityOptional.get());
        } else {
            throw new NoSuchElementException("No se encontró la rutina con ID: " + id);
        }
    }
}
