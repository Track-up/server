package com.gimnsio.libreta.exercise.persistence;


import com.gimnsio.libreta.persistence.enums.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@Entity(name="exercises")
@Data
public class ExerciseEntity {

    @Id
    private String id;
    private String name;
    @Column(name="name_es")
    private String nameEs;
    private Force force;
    private Level level;
    private Mechanic mechanic;
    private Equipment equipment;
    private List<Muscle> primaryMuscles;
    private List<Muscle> secondaryMuscles;
    private List<String> instructions;
    private Category category;
    private List<String> images;
    //private String video;





    public ExerciseEntity(String id) {
        this.id = id;
    }
}


