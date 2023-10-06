package com.gimnsio.libreta.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "exercises_for_workout")
@Data
@NoArgsConstructor
public class ExerciseForWorkoutEntity {

    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private ExerciseEntity exercise;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private WorkoutEntity workout;
}