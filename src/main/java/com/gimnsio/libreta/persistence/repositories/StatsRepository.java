package com.gimnsio.libreta.persistence.repositories;

import com.gimnsio.libreta.persistence.entities.StatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StatsRepository extends JpaRepository<StatsEntity, Long> {

    @Query(value = "delete from stats where user_id = :user_id", nativeQuery=true)
    void deleteByUser(Long user_id);

    @Query(value = "select * from stats where user_id = :user_id", nativeQuery=true)
    StatsEntity findByUser(Long user_id);


}
