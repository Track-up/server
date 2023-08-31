package com.gimnsio.libreta.DTO.users;

import com.gimnsio.libreta.persistence.entities.RoleEntity;
import com.gimnsio.libreta.persistence.entities.RoutineEntity;
import com.gimnsio.libreta.persistence.entities.WorkoutEntity;
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
    private String password;
    private Set<RoutineEntity> routines;
    private Set<RoleEntity> roles;
    private Set<WorkoutEntity> workouts_done;
    private Date dateOfCreation;



    // Add  likes???
    // created_routines: Routine[], etc. ???
}