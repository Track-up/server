package com.gimnsio.libreta.persistence.repositories;

import com.gimnsio.libreta.persistence.entities.RoutineEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutineRepository extends JpaRepository<RoutineEntity,Long> {
    @Query(value = "select * from routines where creator = :user_id", nativeQuery=true)
    Page<RoutineEntity> findByUser(Long user_id, Pageable pageable);
}
