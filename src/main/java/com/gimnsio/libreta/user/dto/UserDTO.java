package com.gimnsio.libreta.user.dto;

import com.gimnsio.libreta.persistence.entities.UserRole;
import com.gimnsio.libreta.workout.persistence.WorkoutEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String username;
    private String email;
    private String image;
    private Set<UserRole> roles;
    private Set<WorkoutEntity> workouts_done;
    private Date dateOfCreation;
    private MeasuresForUserDTO stats;



    // Add  likes???
    // created_routines: Routine[], etc. ???
}