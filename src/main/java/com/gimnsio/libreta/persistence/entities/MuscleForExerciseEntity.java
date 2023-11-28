package com.gimnsio.libreta.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "muscles_for_exercise")
@Data
@NoArgsConstructor
public class MuscleForExerciseEntity {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private ExerciseEntity exercise;

    @ManyToOne
    @JoinColumn(name = "muscle_id")
    private MuscleEntity muscle;

    private Double percent;

    public MuscleForExerciseEntity(ExerciseEntity exercise, MuscleEntity muscle, Double percent) {
        this.exercise = exercise;
        this.muscle = muscle;
        this.percent = percent;
    }
}
