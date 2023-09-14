package com.gimnsio.libreta.Mapper;

import com.gimnsio.libreta.DTO.users.StatsDTO;
import com.gimnsio.libreta.DTO.users.StatsForUserDTO;
import com.gimnsio.libreta.persistence.entities.StatsEntity;
import com.gimnsio.libreta.persistence.entities.StatsHistoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatsMapper {


    public StatsEntity dtoToEntity (StatsDTO statsDTO);

    public StatsHistoryEntity entityToHistory(StatsEntity statsEntity);

    public StatsDTO entityToDto(StatsEntity statsEntity);

    public StatsForUserDTO entityToForUser(StatsEntity statsEntity);

}
