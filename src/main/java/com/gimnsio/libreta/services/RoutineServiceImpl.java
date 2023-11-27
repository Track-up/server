package com.gimnsio.libreta.services;

import com.gimnsio.libreta.DTO.exercises.ExerciseForNewRoutineDTO;
import com.gimnsio.libreta.DTO.exercises.ExerciseForRoutineDTO;
import com.gimnsio.libreta.DTO.routines.*;
import com.gimnsio.libreta.DTO.users.UserDTO;
import com.gimnsio.libreta.Mapper.ExerciseMapper;
import com.gimnsio.libreta.Mapper.RoutineMapper;
import com.gimnsio.libreta.persistence.entities.ExerciseEntity;
import com.gimnsio.libreta.persistence.entities.RoutineEntity;
import com.gimnsio.libreta.persistence.entities.SerieExampleEntity;
import com.gimnsio.libreta.persistence.repositories.RoutineRepository;
import com.gimnsio.libreta.persistence.repositories.SerieExampleRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoutineServiceImpl implements RoutineService {

    final private RoutineRepository routineRepository;

    final private SerieExampleRepository serieExampleRepository;

    final private RoutineMapper routineMapper;

    final private ExerciseMapper exerciseMapper;

    final private ExerciseService exerciseService;

    final private UserService userService;

    public RoutineServiceImpl(RoutineRepository routineRepository, SerieExampleRepository serieExampleRepository, RoutineMapper routineMapper, ExerciseMapper exerciseMapper, ExerciseService exerciseService, UserService userService) {
        this.serieExampleRepository = serieExampleRepository;
        this.routineMapper = routineMapper;
        this.routineRepository = routineRepository;
        this.exerciseMapper = exerciseMapper;
        this.exerciseService = exerciseService;
        this.userService = userService;
    }

    @Override
    public List<RoutineDTO> getAllRoutines(Pageable pageable) {
        List<RoutineEntity> routineEntities = routineRepository.findAll(pageable).stream().collect(Collectors.toList());
        List<RoutineDTO> routines = routineEntities.stream().map(routineEntity -> {

            return routineMapper.entityToDTO(routineEntity);

        }).collect(Collectors.toList());

        for (int i = 0; i < routines.size(); i++) {
            routines.set(i,setExercises(routineEntities.get(i),routines.get(i)));
        }
        return  routines;
    }

    @Override
    public RoutineDTO getRoutineById(long id) {

        Optional<RoutineEntity> routineEntityOptional = routineRepository.findById(id);

        if (routineEntityOptional.isPresent()) {
            RoutineDTO routineDTO = routineMapper.entityToDTO(routineEntityOptional.get());
            return setExercises(routineEntityOptional.get(), routineDTO);
        } else {
            throw new NoSuchElementException("No se encontró la rutina con ID: " + id);//NO SUBE AL FRONT
        }
    }

    private RoutineDTO setExercises(RoutineEntity routineEntity, RoutineDTO routineDTO) {
        List<ExerciseForRoutineDTO> exercises = new ArrayList<>();
        List<SerieExampleEntity> series = serieExampleRepository.findByRoutine(routineEntity);
        for (SerieExampleEntity serie:series) {
            boolean isExercise= false;
            for (ExerciseForRoutineDTO exercise:exercises) {
                if (exercise.getId().equals(serie.getExercise().getId())){
                    isExercise = true;
                    break;
                }
            }
            if (!isExercise){
                exercises.add(new ExerciseForRoutineDTO(serie.getExercise().getId(),serie.getExercise().getName(),serie.getExercise().getGifUrl(),serie.getExercise().getDescription(),series.stream().filter(serieStream-> serie.getExercise().getId().equals(serieStream.getExercise().getId())).count()));
            }

        }
        routineDTO.setExercises(exercises);
        return routineDTO;
    }

    @Override
    public List<RoutineDTO> getRoutinesByUserCreator(UserDTO userDTO) {
        return null;
    }

    @Override
    public RoutineIdDTO createRoutine(RoutineNewDTO routineNewDTO) {

        checkUser(routineNewDTO.getCreatorId());
        if (routineNewDTO.getImage().isEmpty()) {
            routineNewDTO.setImage(exerciseService.getExerciseById(routineNewDTO.getExercises().get(0).getId()).getGifUrl());//para un futuro en el mapper directamente
        }
        RoutineEntity routineEntity = routineMapper.newToEntity(routineNewDTO);
        routineEntity = routineRepository.save(routineEntity);
        for (ExerciseForNewRoutineDTO exercise:routineNewDTO.getExercises()) {
            ExerciseEntity exerciseEntity = exerciseService.getExerciseById(exercise.getId());
            for (int i = 0; i < exercise.getNumSeries(); i++) {
                serieExampleRepository.save(new SerieExampleEntity(exerciseEntity,routineEntity));
            }
        }
        return routineMapper.entityToIdDTO(routineEntity);
    }

    @Override
    public RoutineDTO updateRoutine(RoutineEditDTO routineEditDTO) {
        return null;
//        checkRoutine(routineEditDTO.getId());
//        checkUser(routineEditDTO.getCreatorId());
//        Optional<RoutineEntity> routineEntityOptional = routineRepository.findById(routineEditDTO.getId());
//        if (routineEntityOptional.isEmpty()) {
//            throw new NoSuchElementException("No se encontró la rutina con ID: " + routineEditDTO.getId());
//        }
//        RoutineEntity routineEntity = routineEntityOptional.get();
//        routineMapper.UpdateRoutineFromEditDTO(routineEditDTO, routineEntity);
////        RoutineEntity routineEntity = routineMapper.editToEntity(routineEditDTO);
////        routineEntity.setDateOfCreation(routineEntityOptional.get().getDateOfCreation());
//
//        return routineMapper.entityToDTO(routineRepository.save(routineEntity));
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
    public List<RoutineDTO> getRoutinesByName(String name, Pageable pageable) {
        List<RoutineEntity> routineEntities = routineRepository.findByName(name.toLowerCase(), pageable);

        List<RoutineDTO> routines = routineEntities.stream()
                .map(routineMapper::entityToDTO)
                .collect(Collectors.toList());

        for (int i = 0; i < routines.size(); i++) {
            routines.set(i,setExercises(routineEntities.get(i),routines.get(i)));
        }
        return  routines;
    }

//    @Override
//    public List<List<RoutineBasicsDTO>> getRoutinesByString(String name, Pageable pageable) {
//        List<List<?>> pageList = new ArrayList<>();
//        pageList.add(getRoutinesByName(name, pageable));
//        pageList.add(getRoutinesByUsername(name, pageable));
//        return pageList;
//    }

    @Override
    public RoutineForWorkoutDTO getRoutineForWorkout(long id) {

        Optional<RoutineEntity> routineEntityOptional = routineRepository.findById(id);

        if (routineEntityOptional.isPresent()) {
            RoutineForWorkoutDTO routineForWorkoutDTO = routineMapper.entityToWorkout(routineEntityOptional.get());

            return routineForWorkoutDTO;
        } else {
            throw new NoSuchElementException("No se encontró la rutina con ID: " + id);
        }
    }
}
