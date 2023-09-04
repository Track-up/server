package com.gimnsio.libreta.persistence.repositories;

import com.gimnsio.libreta.persistence.entities.RoutineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoutineRepository extends JpaRepository<RoutineEntity,Long> {
    @Query(value = "select * from routines where creator = :user_id", nativeQuery=true)
    public Set<RoutineEntity> findByUser(Long user_id);
}
