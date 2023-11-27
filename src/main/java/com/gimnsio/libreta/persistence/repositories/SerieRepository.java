package com.gimnsio.libreta.persistence.repositories;

import com.gimnsio.libreta.persistence.entities.SerieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SerieRepository extends JpaRepository<SerieEntity, Long> {

//    @Query(value = "SELECT *" +
//            "FROM series s" +
//            "JOIN workouts w ON w.id = s.workout_id" +
//            "WHERE exercise_id = :exerciseId" +
//            "  AND worker_id = :userId" +
//            "  AND w.id = (SELECT MAX(id) FROM workouts)" +
//            "ORDER BY date;", nativeQuery = true)
//    public List<SerieEntity> findByExerciseForUser(Long exerciseId, Long userId);

//    @Query(value = "select s from series s where s.exercise.id = :exerciseId and s.workout.id = (select MAX(workout.id) from series where exercise.id = :exerciseId and workout.worker.id = :userId)")//ESTO FUNCIONA
//    public List<SerieEntity> findLastByExerciseAndUser(@Param("exerciseId") Long exerciseId, @Param("userId") Long userId);

    @Query(value = "select s from series s where s.workout.id = (select MAX(s2.workout.id) from series s2 where s2.exercise.id = :exerciseId and s2.workout.id in (select id from workouts w where w.worker.id = :userId)) and s.exercise.id = :exerciseId")
    List<SerieEntity> findLastByExerciseAndUser(@Param("exerciseId") Long exerciseId, @Param("userId") Long userId);


//    public List<SerieEntity> findByExerciseForWorkout(ExerciseForWorkoutEntity exerciseForWorkout);





}
