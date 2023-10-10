package com.gimnsio.libreta.persistence.repositories;

import com.gimnsio.libreta.persistence.entities.ExerciseForWorkoutEntity;
import com.gimnsio.libreta.persistence.entities.WorkoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseForWorkoutRepository extends JpaRepository <ExerciseForWorkoutEntity,Long> {

    List<ExerciseForWorkoutEntity> findByWorkout(WorkoutEntity workout);

}
