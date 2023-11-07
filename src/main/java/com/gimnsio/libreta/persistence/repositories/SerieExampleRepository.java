package com.gimnsio.libreta.persistence.repositories;

import com.gimnsio.libreta.persistence.entities.ExerciseEntity;
import com.gimnsio.libreta.persistence.entities.RoutineEntity;
import com.gimnsio.libreta.persistence.entities.SerieExampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SerieExampleRepository extends JpaRepository<SerieExampleEntity, Long> {

    List<SerieExampleEntity> findByRoutine(RoutineEntity routine);

    List<SerieExampleEntity> findByRoutineAndExercise(RoutineEntity routine, ExerciseEntity exercise);

}
