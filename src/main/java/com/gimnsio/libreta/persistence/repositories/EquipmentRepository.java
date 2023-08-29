package com.gimnsio.libreta.persistence.repositories;

import com.gimnsio.libreta.persistence.entities.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<EquipmentEntity,Long> {
    EquipmentEntity findByName(String name);

}
