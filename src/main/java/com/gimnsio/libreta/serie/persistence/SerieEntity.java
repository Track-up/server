package com.gimnsio.libreta.serie.persistence;

import com.gimnsio.libreta.exercise.persistence.ExerciseEntity;
import com.gimnsio.libreta.workout.persistence.WorkoutEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public String toString() {
        return "SerieEntity{" +
                "id=" + id +
                ", exercise=" + exercise +
                ", reps=" + reps +
                ", kg=" + kg +
                '}';
    }
}
