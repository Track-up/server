package com.gimnsio.libreta.persistence.repositories;

import com.gimnsio.libreta.persistence.entities.MeasuresEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasuresRepository extends JpaRepository<MeasuresEntity,Long> {

    @Query(value = "select * from measures where user_id = :id", nativeQuery=true)
    MeasuresEntity findByUser(Long id);

}
