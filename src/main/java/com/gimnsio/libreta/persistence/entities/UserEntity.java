package com.gimnsio.libreta.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@Entity(name = "users")
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @Size(max = 30)
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Email
    @Size(max = 80)
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotBlank
    private String password;

    @ManyToMany
    @JoinTable(
            name = "users_routines",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "routine_id")
    )
    private Set<RoutineEntity> routines;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = RoleEntity.class, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles;

//    private Set<Workout> workouts_done;

//    private Date dateOfCreation;
}
