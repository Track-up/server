package com.gimnsio.libreta.routine.service;

import com.gimnsio.libreta.exercise.dto.ExerciseForNewRoutineDTO;
import com.gimnsio.libreta.exercise.dto.ExerciseForRoutineDTO;
import com.gimnsio.libreta.user.dto.UserDTO;
import com.gimnsio.libreta.routine.dto.*;
import com.gimnsio.libreta.serie.service.SerieExampleService;
import com.gimnsio.libreta.exercise.service.ExerciseService;
import com.gimnsio.libreta.user.service.UserService;
import com.gimnsio.libreta.workout.dto.WorkoutDTO;
import com.gimnsio.libreta.routine.mapper.RoutineMapper;
import com.gimnsio.libreta.exercise.persistence.ExerciseEntity;
import com.gimnsio.libreta.routine.persistence.RoutineEntity;
import com.gimnsio.libreta.serie.persistence.SerieExampleEntity;
import com.gimnsio.libreta.routine.persistence.RoutineRepository;
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

    final private RoutineMapper routineMapper;

    final private SerieExampleService serieExampleService;

    final private ExerciseService exerciseService;

    final private UserService userService;


    public RoutineServiceImpl(RoutineRepository routineRepository, SerieExampleService serieExampleService, RoutineMapper routineMapper, ExerciseService exerciseService, UserService userService) {
        this.serieExampleService = serieExampleService;
        this.routineMapper = routineMapper;
        this.routineRepository = routineRepository;
        this.exerciseService = exerciseService;
        this.userService = userService;
//        this.workoutService = workoutService;
    }

    @Override
    public List<RoutineDTO> getAllRoutines(Pageable pageable) {
        List<RoutineEntity> routineEntities = routineRepository.findAll(pageable).stream().collect(Collectors.toList());
        return getRoutineDTOs(routineEntities);
    }

    private List<RoutineDTO> getRoutineDTOs(List<RoutineEntity> routineEntities) {
        List<RoutineDTO> routines = new ArrayList<>();

        for (RoutineEntity routineEntity : routineEntities) {
            RoutineDTO routineDTO = routineMapper.entityToDTO(routineEntity);
            routineDTO.setExercises(new ArrayList<>());
            setExercisesForRoutineDTO(routineEntity, routineDTO);
            routines.add(routineDTO);
        }
        return routines;
    }

    @Override
    public RoutineDTO getRoutineById(long id) {
        RoutineEntity routineEntity = findRoutineById(id);
        RoutineDTO routineDTO = routineMapper.entityToDTO(routineEntity);
        routineDTO.setExercises(new ArrayList<>());
        setExercisesForRoutineDTO(routineEntity, routineDTO);
        return routineDTO;

    }

    private void setExercisesForRoutineDTO(RoutineEntity routineEntity, RoutineDTO routineDTO) {

        for (SerieExampleEntity serie : routineEntity.getSeries()) {
            if (!routineDTO.getExercises().isEmpty() && serie.getExercise().getId().equals(routineDTO.getExercises().get(routineDTO.getExercises().size() - 1).getId())) {
                routineDTO.getExercises().get(routineDTO.getExercises().size() - 1).setNumSeries(routineDTO.getExercises().get(routineDTO.getExercises().size() - 1).getNumSeries() + 1);
            } else {
                routineDTO.getExercises().add(new ExerciseForRoutineDTO(
                        serie.getExercise().getId(), serie.getExercise().getName(), serie.getExercise().getImages(), null, 1L)
                );
            }
        }
    }

    private RoutineDTO setExercises(RoutineEntity routineEntity, RoutineDTO routineDTO) {
        List<ExerciseForRoutineDTO> exercises = new ArrayList<>();
        List<SerieExampleEntity> series = serieExampleService.getSeriesOfRoutine(routineEntity);
        for (SerieExampleEntity serie : series) {
            boolean isExercise = false;
            for (ExerciseForRoutineDTO exercise : exercises) {
                if (exercise.getId().equals(serie.getExercise().getId())) {
                    isExercise = true;
                    break;
                }
            }
            if (!isExercise) {
//                exercises.add(new ExerciseForRoutineDTO(serie.getExercise().getId(), serie.getExercise().getName(), serie.getExercise().getImage(), serie.getExercise().getDescription(), series.stream().filter(serieStream -> serie.getExercise().getId().equals(serieStream.getExercise().getId())).count()));
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
    public Long createRoutine(RoutineNewDTO routineNewDTO) {

        checkUser(routineNewDTO.getCreatorId());
        if (routineNewDTO.getImage().isEmpty()) {
//            routineNewDTO.setImage(exerciseService.getExerciseById(routineNewDTO.getExercises().get(0).getId()).getImage());//para un futuro en el mapper directamente
        }
        RoutineEntity routineEntity = routineMapper.newToEntity(routineNewDTO);


        routineEntity = routineRepository.save(routineEntity);
        for (ExerciseForNewRoutineDTO exercise : routineNewDTO.getExercises()) {
            ExerciseEntity exerciseEntity = exerciseService.getExerciseById(exercise.getId());
            for (int i = 0; i < exercise.getNumSeries(); i++) {
                serieExampleService.save(new SerieExampleEntity(exerciseEntity, routineEntity));
            }
        }
        return routineEntity.getId();
    }

    @Override
    public Long createRoutineCopy(Long id, RoutineNewDTO routineNewDTO) {

        checkUser(routineNewDTO.getCreatorId());
        if (routineNewDTO.getImage().isEmpty()) {
//            routineNewDTO.setImage(exerciseService.getExerciseById(routineNewDTO.getExercises().get(0).getId()).getImage());//para un futuro en el mapper directamente
        }
        RoutineEntity routineEntity = routineMapper.newToEntity(routineNewDTO);

        routineEntity.setRoutineCoped(findRoutineById(id));
        routineEntity = routineRepository.save(routineEntity);
        for (ExerciseForNewRoutineDTO exercise : routineNewDTO.getExercises()) {
            ExerciseEntity exerciseEntity = exerciseService.getExerciseById(exercise.getId());
            for (int i = 0; i < exercise.getNumSeries(); i++) {
                serieExampleService.save(new SerieExampleEntity(exerciseEntity, routineEntity));
            }
        }
        return routineEntity.getId();
    }

    @Override
    public RoutineDTO updateRoutine(Long id, RoutineEditDTO routineEditDTO) {//deprecated
        checkRoutine(id);
        checkUser(routineEditDTO.getCreatorId());
        Optional<RoutineEntity> routineEntityOptional = routineRepository.findById(routineEditDTO.getId());
        if (routineEntityOptional.isEmpty()) {
            throw new NoSuchElementException("No se encontró la rutina con ID: " + routineEditDTO.getId());
        }
        RoutineEntity routineEntity = routineEntityOptional.get();
        routineMapper.UpdateRoutineFromEditDTO(routineEditDTO, routineEntity);

        for (ExerciseForNewRoutineDTO exercise : routineEditDTO.getExercises()) {

            ExerciseEntity exerciseEntity = exerciseService.getExerciseById(exercise.getId());
            List<SerieExampleEntity> serieExampleEntities = serieExampleService.findByRoutineAndExercise(routineEntity, exerciseEntity);


            if (exercise.getNumSeries() > serieExampleEntities.size()) {
                while (serieExampleEntities.size() < exercise.getNumSeries()) {
                    serieExampleEntities.add(serieExampleService.save(new SerieExampleEntity(exerciseEntity, routineEntity)));
                }
            } else if (exercise.getNumSeries() < serieExampleEntities.size()) {
                while (serieExampleEntities.size() > exercise.getNumSeries()) {
                    serieExampleService.delete(serieExampleEntities.get(0));
                    serieExampleEntities.remove(0);
                }
            }
        }
        List<SerieExampleEntity> serieExampleEntities = serieExampleService.getSeriesOfRoutine(routineEntity);
        for (SerieExampleEntity serie : serieExampleEntities) {
            for (ExerciseForNewRoutineDTO exercise : routineEditDTO.getExercises()) {
//                if (serie.getExercise().getId() == exercise.getId()) {
                break;
            }
            serieExampleService.delete(serie);
        }
//        }
        RoutineDTO routineDTO = routineMapper.entityToDTO(routineRepository.save(routineEntity));
        setExercises(routineEntity, routineDTO);
        return routineDTO;
    }


    private void checkUser(Long id) {
        userService.getUserEntityById(id);
    }

    private void checkRoutine(long id) {

    }

    @Override
    public void deleteRoutine(long id) {

            RoutineEntity routine = findRoutineById(id);
            routineRepository.findByRoutineCoped(routine).forEach(routineEntity -> {
                routineEntity.setRoutineCoped(null);
                routineRepository.save(routineEntity);
            });
        routineRepository.deleteById(id);


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
        return getRoutineDTOs(routineEntities);
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

    @Override
    public RoutineDTO getRoutineByWorkout(Long id) {

        WorkoutDTO workoutDTO = null;//workoutService.getWorkoutById(id);
        RoutineDTO routineDTO = new RoutineDTO();

        workoutDTO.getExercises().forEach(exercise -> {
            routineDTO.getExercises().add(new ExerciseForRoutineDTO(exercise.getExerciseId(), exercise.getName(), exercise.getImages(), null, exercise.getSeries().stream().count()));
        });

        return routineDTO;
    }

    private RoutineEntity findRoutineById(Long id) {
        return routineRepository.findById(id).orElse(null);
    }
}
