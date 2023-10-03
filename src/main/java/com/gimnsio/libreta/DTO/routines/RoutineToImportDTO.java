package com.gimnsio.libreta.DTO.routines;

import com.gimnsio.libreta.persistence.entities.BodyPartEntity;
import com.gimnsio.libreta.persistence.entities.ExerciseEntity;
import com.gimnsio.libreta.persistence.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoutineToImportDTO {


    private Long id;
    private String name;

    private List<ExerciseEntity> exercises;

    private UserEntity creator;

    private List<BodyPartEntity> bodyParts;
    private Date dateOfCreation;
    private Long durationMinutes;
    private boolean isPublic;


}


