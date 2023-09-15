package com.gimnsio.libreta.persistence.repositories;

import com.gimnsio.libreta.persistence.entities.StatsHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatsHistoryRepository extends JpaRepository<StatsHistoryEntity, Long> {
}
