package com.gimnsio.libreta.serie.persistence;

import com.gimnsio.libreta.exercise.persistence.ExerciseEntity;
import com.gimnsio.libreta.routine.persistence.RoutineEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity(name="series_examples")
public class SerieExampleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private ExerciseEntity exercise;

    @ManyToOne
    @JoinColumn(name = "routine_id")
    @ToString.Exclude
    private RoutineEntity routine;

    public SerieExampleEntity(ExerciseEntity exercise, RoutineEntity routine) {
        this.exercise = exercise;
        this.routine = routine;
    }


}
