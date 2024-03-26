package com.gimnsio.libreta.workout.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<WorkoutEntity,Long> {
    @Query(value = "select * from workouts where worker_id = :userId and end_date is not null order by end_date desc limit :until", nativeQuery=true)
    List<WorkoutEntity> findLastWorkouts(Long userId, Integer until);

//    @Query(value = "select w from workout w where w.worker = :userId and w.exercisesOfWorkout.exercise.id")
//    public WorkoutEntity findByExerciseAndUser(Long exerciseId, Long userId);

//    @Query(value = "select * from workouts where user_id = :exerciseId and ", nativeQuery=true)
//    public WorkoutEntity findByExerciseForUser(Long exerciseId, Long userId);

}
