package com.gimnsio.libreta.serie.persistence;

import com.gimnsio.libreta.exercise.persistence.ExerciseEntity;
import com.gimnsio.libreta.routine.persistence.RoutineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SerieExampleRepository extends JpaRepository<SerieExampleEntity, Long> {

    List<SerieExampleEntity> findByRoutine(RoutineEntity routine);

    List<SerieExampleEntity> findByRoutineAndExercise(RoutineEntity routine, ExerciseEntity exercise);

;
}
