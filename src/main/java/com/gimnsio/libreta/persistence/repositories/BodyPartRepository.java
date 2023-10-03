package com.gimnsio.libreta.persistence.repositories;

import com.gimnsio.libreta.persistence.entities.BodyPartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodyPartRepository extends JpaRepository<BodyPartEntity, Long> {

    BodyPartEntity findByName(String bodyPartName);
}