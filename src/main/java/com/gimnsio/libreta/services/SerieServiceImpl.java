package com.gimnsio.libreta.services;

import com.gimnsio.libreta.DTO.exercises.ExerciseForWorkoutDTO;
import com.gimnsio.libreta.DTO.serie.SerieForExerciseDTO;
import com.gimnsio.libreta.DTO.workout.WorkoutDTO;
import com.gimnsio.libreta.Mapper.SerieMapper;
import com.gimnsio.libreta.exception.ApiRequestException;
import com.gimnsio.libreta.persistence.entities.ExerciseEntity;
import com.gimnsio.libreta.persistence.entities.SerieEntity;
import com.gimnsio.libreta.persistence.entities.WorkoutEntity;
import com.gimnsio.libreta.persistence.repositories.SerieRepository;
import com.gimnsio.libreta.persistence.repositories.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SerieServiceImpl implements SerieService {

    @Autowired
    SerieRepository serieRepository;

    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    SerieMapper serieMapper;

    @Override
    public SerieEntity getSerieEntityById(long id) {
        Optional<SerieEntity> serieEntityOptional = serieRepository.findById(id);
        if (serieEntityOptional.isPresent()) {
            return serieEntityOptional.get();
        } else {
            throw new ApiRequestException("No se encontró la serie con el ID: " + id);
        }
    }

    @Override
    public SerieForExerciseDTO saveSerie(SerieEntity serie) {
        return serieMapper.entityToForExerciseDTO(serieRepository.save(serie));
    }

    @Override
    public SerieForExerciseDTO createSerie(ExerciseEntity exercise, long difficulty, SerieEntity serie, WorkoutEntity workout) {
        if (difficulty == 2) {
            if (serie.getReps() > 12) {
                serie.setReps(serie.getReps() + 1);
            } else {
                serie.setReps(6L);
                serie.setKg(serie.getKg() + 1);
            }
        } else if (difficulty == 3) {
            if (serie.getReps() > 12) {
                serie.setReps(serie.getReps() + 2);
            } else {
                serie.setReps(6L);
                serie.setKg(serie.getKg() + 2);
            }
        }
        return saveSerie(new SerieEntity(exercise, serie.getReps(), serie.getKg(), workout));
    }


    @Override
    public List<SerieEntity> getSeriesOfLastWorkoutFromExerciseAndUser(String exerciseId, long userId) {
        return serieRepository.findLastByExerciseAndUser(exerciseId, userId);
    }

    @Override
    public void saveAll(List<SerieEntity> seriesEntity) {
        serieRepository.saveAll(seriesEntity);
    }

    @Override
    public void deleteSerie(SerieEntity serieEntity) {
        serieRepository.delete(serieEntity);
    }

    @Override
    public List<SerieEntity> getSeriesOfWorkout(WorkoutEntity workoutEntity) {
        return serieRepository.findByWorkout(workoutEntity);
    }

    @Override
    public List<ExerciseForWorkoutDTO> getSeriesOfExerciseForWorkout(WorkoutEntity workoutEntity) {
        List<SerieEntity> series = getSeriesOfWorkout(workoutEntity);
        List<ExerciseForWorkoutDTO> exercises = new ArrayList<>();
        for (SerieEntity serie : series) {
            if (!exercises.isEmpty() && serie.getExercise().getId() == exercises.get(exercises.size() - 1).getExerciseId()) {
                exercises.get(exercises.size() - 1).getSeries().add(serieEntityToDTO(serie));
            } else {
                ExerciseForWorkoutDTO exerciseForWorkoutDTO = new ExerciseForWorkoutDTO();
                exerciseForWorkoutDTO.setExerciseId(serie.getExercise().getId());
                exerciseForWorkoutDTO.setName(serie.getExercise().getName());
                exerciseForWorkoutDTO.setImage(serie.getExercise().getImages().get(0));
                exerciseForWorkoutDTO.setSeries(new ArrayList<>());
                exerciseForWorkoutDTO.getSeries().add(serieEntityToDTO(serie));
                exercises.add(exerciseForWorkoutDTO);
            }
        }
        return exercises;
    }


        @Override
        public SerieForExerciseDTO serieEntityToDTO (SerieEntity serieEntity){
            SerieForExerciseDTO serieForExerciseDTO = new SerieForExerciseDTO();
            serieForExerciseDTO.setId(serieEntity.getId());
            serieForExerciseDTO.setKg(serieEntity.getKg());
            serieForExerciseDTO.setReps(serieEntity.getReps());
            return serieForExerciseDTO;
        }


    }
