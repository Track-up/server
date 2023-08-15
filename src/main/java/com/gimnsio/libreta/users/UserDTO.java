package com.gimnsio.libreta.users;

import com.gimnsio.libreta.domain.Routine;
import com.gimnsio.libreta.domain.Workout;
import com.gimnsio.libreta.persistence.entities.RoleEntity;
import com.gimnsio.libreta.persistence.entities.RoutineEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    private Set<Routine> routines;
    private Set<RoleEntity> roles;
    private Set<Workout> workouts_done;
    private Date dateOfCreation;


    // Add  likes???
    // created_routines: Routine[], etc. ???
}