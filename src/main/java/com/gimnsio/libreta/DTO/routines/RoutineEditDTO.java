package com.gimnsio.libreta.DTO.routines;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RoutineEditDTO {

    private long id;
    private String name;
    private String image;
    private List<Long> exercisesId;
    private Long creatorId;
    private boolean isPublic;

}