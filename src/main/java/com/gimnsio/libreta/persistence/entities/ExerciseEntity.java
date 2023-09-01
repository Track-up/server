package com.gimnsio.libreta.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Entity(name="exercises")
@Data
public class ExerciseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String gifUrl;
    private String description;

    @ManyToOne
    @JoinColumn(name = "body_part")
    private BodyPartEntity bodyPart;

    @ManyToOne
    @JoinColumn(name = "equipment")
    private EquipmentEntity equipment;

    @ManyToOne
    @JoinColumn(name = "target")
    private MuscleEntity target;

    public ExerciseEntity(Long id) {
        this.id = id;
    }
}