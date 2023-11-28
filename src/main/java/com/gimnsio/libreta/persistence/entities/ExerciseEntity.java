package com.gimnsio.libreta.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@Entity(name="exercises")
@Data
public class ExerciseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String image;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "exercises_body_parts",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "body_part_id")
    )
    private List<BodyPartEntity> bodyParts;

    @ManyToOne
    @JoinColumn(name = "equipment")
    private EquipmentEntity equipment;



    public ExerciseEntity(Long id) {
        this.id = id;
    }
}