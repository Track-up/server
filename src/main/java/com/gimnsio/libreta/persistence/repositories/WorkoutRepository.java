package com.gimnsio.libreta.persistence.repositories;

import com.gimnsio.libreta.persistence.entities.WorkoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends JpaRepository<WorkoutEntity,Long> {

//    @Query(value = "select w from workout w where w.worker = :userId and w.exercisesOfWorkout.exercise.id")
//    public WorkoutEntity findByExerciseAndUser(Long exerciseId, Long userId);

//    @Query(value = "select * from workouts where user_id = :exerciseId and ", nativeQuery=true)
//    public WorkoutEntity findByExerciseForUser(Long exerciseId, Long userId);

}
