package com.gimnsio.libreta.persistence.repositories;

import com.gimnsio.libreta.persistence.entities.MuscleForExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MuscleForExerciseRepository extends JpaRepository<MuscleForExerciseEntity,Long> {


    List<MuscleForExerciseEntity> findByExercise_Id(Long id);
}
