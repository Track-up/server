package com.gimnsio.libreta.services;

import com.gimnsio.libreta.persistence.entities.EquipmentEntity;

import java.util.Set;


public interface EquipmentService {

    public Set<EquipmentEntity> createEquipments(Set<EquipmentEntity> equipments);

}
