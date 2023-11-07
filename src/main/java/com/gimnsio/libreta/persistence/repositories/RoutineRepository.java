package com.gimnsio.libreta.persistence.repositories;

import com.gimnsio.libreta.persistence.entities.RoutineEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoutineRepository extends JpaRepository<RoutineEntity,Long> {

    @Query(value = "select r from routines r where r.creator.id = :user_id")
    List<RoutineEntity> findByUser(@Param("user_id") Long user_id, Pageable pageable);




    @Query(value = "select r from routines r where r.creator.username like %:username%")
    List<RoutineEntity> findByUsername(@Param("username") String username, Pageable pageable);

    @Query(value = "select r from routines r where lower(r.name) like %:name%")
    List<RoutineEntity> findByName(@Param("name") String name, Pageable pageable);
}
