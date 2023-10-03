package com.gimnsio.libreta.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "routines")
public class RoutineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String image;

    @ManyToMany
    @JoinTable(
            name = "routines_exercises",
            joinColumns = @JoinColumn(name = "routine_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )
    private List<ExerciseEntity> exercises;

    // Relación muchos a uno con la clase User
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private UserEntity creator;
    @ManyToMany
    @JoinTable(
            name = "routines_body_part",
            joinColumns = @JoinColumn(name = "routine_id"),
            inverseJoinColumns = @JoinColumn(name = "body_part_id")
    )
    private List<BodyPartEntity> bodyParts;
    private Date dateOfCreation;
    private Date dateOfLastEdition;
    private boolean isPublic;



}
