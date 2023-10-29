package com.gimnsio.libreta.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name="series")
public class SerieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private ExerciseEntity exercise;
    private long reps;
    private double kg;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private WorkoutEntity workout;

    public SerieEntity(ExerciseEntity exercise, long reps, double kg, WorkoutEntity workout) {
        this.exercise = exercise;
        this.reps = reps;
        this.kg = kg;
        this.workout = workout;
    }
}
