package com.gimnsio.libreta.services;

import com.gimnsio.libreta.DTO.users.StatsDTO;
import com.gimnsio.libreta.persistence.entities.StatsEntity;

public interface StatsService {

    public StatsEntity createStats(StatsDTO statsDTO);

}
