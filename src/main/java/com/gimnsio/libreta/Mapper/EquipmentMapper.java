package com.gimnsio.libreta.Mapper;

import com.gimnsio.libreta.DTO.equipment.EquipmentForExerciseDTO;
import com.gimnsio.libreta.persistence.entities.EquipmentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipmentMapper {

    public EquipmentForExerciseDTO entityToForExerciseDTO(EquipmentEntity equipmentEntity);

}
