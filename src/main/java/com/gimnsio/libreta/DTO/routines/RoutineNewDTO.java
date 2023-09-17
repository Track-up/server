package com.gimnsio.libreta.DTO.routines;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RoutineNewDTO {

    private String name;
    private String image;
    private List<Long> exercisesId;
    private Long creatorId;

}
