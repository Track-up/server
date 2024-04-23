package com.gimnsio.libreta.graph.service;

import com.gimnsio.libreta.graph.dto.GraphTrackExerciseDTO;

import java.util.Date;

public interface GraphService {

    public GraphTrackExerciseDTO getGraphOfExercise(Long userId, Date until, String exerciseId);


}
