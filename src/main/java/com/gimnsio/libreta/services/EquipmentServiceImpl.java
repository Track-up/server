package com.gimnsio.libreta.services;

import com.gimnsio.libreta.persistence.entities.EquipmentEntity;
import com.gimnsio.libreta.persistence.repositories.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    EquipmentRepository equipmentRepository;
    @Override
    public Set<EquipmentEntity> createEquipments(Set<EquipmentEntity> equipments) {

        Set<EquipmentEntity> equipmentsSaved = new HashSet<>();
        for (EquipmentEntity equipment: equipments) {
            equipmentsSaved.add(equipmentRepository.save(equipment));
        }

        return equipmentsSaved;
    }
}
