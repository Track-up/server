package com.gimnsio.libreta.graph.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GraphTrackExerciseDTO {

    private String exerciseName;
    private List<DateAndWeightDTO> data;


}
