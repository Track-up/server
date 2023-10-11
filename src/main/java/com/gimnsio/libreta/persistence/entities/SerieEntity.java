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
    @JoinColumn(name = "exercise_for_workout_id")
    private ExerciseForWorkoutEntity exerciseForWorkout;
    private int reps;
    private double kg;

}
