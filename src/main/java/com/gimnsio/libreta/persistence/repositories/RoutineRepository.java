package com.gimnsio.libreta.persistence.repositories;

import com.gimnsio.libreta.persistence.entities.RoutineEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutineRepository extends JpaRepository<RoutineEntity,Long> {

    @Query(value = "select r from routines r where r.creator.id = :user_id")
    Page<RoutineEntity> findByUser(Long user_id, Pageable pageable);




    @Query(value = "select r from routines r where r.creator.username like %:username%")
    Page<RoutineEntity> findByUsername(String username, Pageable pageable);

    @Query(value = "select r from routines r where r.name like %:name%")
    Page<RoutineEntity> findByName(String name, Pageable pageable);
}
