package com.gimnsio.libreta.graph.service;

import com.gimnsio.libreta.graph.dto.DateAndWeightDTO;
import com.gimnsio.libreta.graph.dto.GraphTrackExerciseDTO;
import com.gimnsio.libreta.serie.dto.SerieForExerciseDTO;
import com.gimnsio.libreta.workout.dto.WorkoutDTO;
import com.gimnsio.libreta.workout.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GraphServiceImpl implements GraphService{
    @Autowired
    WorkoutService workoutService;

    public GraphTrackExerciseDTO getGraphOfExercise(Long userId, Date until, String exerciseId){

        List<DateAndWeightDTO> graph = new ArrayList<>();
        List<WorkoutDTO> workouts = workoutService.getLastWorkoutsOfUserByExercise( userId,  until,  exerciseId);
        for(WorkoutDTO workout : workouts){
            double bestRM = 0;
            for(SerieForExerciseDTO serie : workout.getExercises().get(0).getSeries()){
                double rm = calculateRM(serie.getReps(), serie.getKg());
                if(rm > bestRM){
                    bestRM = rm;
                }
            }
            graph.add(new DateAndWeightDTO(workout.getId(),workout.getCreationDate(), bestRM));
        }
        return new GraphTrackExerciseDTO(workouts.get(0).getExercises().get(0).getName(), graph);

    }

    private double calculateRM(long reps, double weight){
        return weight * ((double) 36 / (37 - reps));
    }

}
