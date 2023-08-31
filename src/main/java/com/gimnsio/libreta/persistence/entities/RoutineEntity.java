package com.gimnsio.libreta.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "routines")
public class RoutineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "routines_exercises",
            joinColumns = @JoinColumn(name = "routine_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )
    private List<ExerciseEntity> exercises;

    // Relación muchos a uno con la clase User
    @ManyToOne
    @JoinColumn(name = "creator")
    private UserEntity creator;
    @ManyToMany
    @JoinTable(
            name = "routines_body_part",
            joinColumns = @JoinColumn(name = "routine_id"),
            inverseJoinColumns = @JoinColumn(name = "body_part_id")
    )
    private List<BodyPartEntity> bodyParts;
    private Date dateOfCreation;
    private Long durationMinutes;
    private boolean isPublic;


}
