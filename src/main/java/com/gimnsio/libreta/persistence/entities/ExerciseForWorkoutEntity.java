package com.gimnsio.libreta.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "exercises_for_workout")
@Data
@NoArgsConstructor
public class ExerciseForWorkoutEntity {

    private long id;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private ExerciseEntity exercise;

    @ManyToOne
    private WorkoutEntity workout;
}
