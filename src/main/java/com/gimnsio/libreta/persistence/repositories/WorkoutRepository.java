package com.gimnsio.libreta.persistence.repositories;

import com.gimnsio.libreta.persistence.entities.WorkoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends JpaRepository<WorkoutEntity,Long> {
}
