package com.gimnsio.libreta.services;

import com.gimnsio.libreta.DTO.users.StatsDTO;
import com.gimnsio.libreta.Mapper.StatsMapper;
import com.gimnsio.libreta.persistence.entities.StatsEntity;
import com.gimnsio.libreta.persistence.entities.StatsHistoryEntity;
import com.gimnsio.libreta.persistence.repositories.StatsHistoryRepository;
import com.gimnsio.libreta.persistence.repositories.StatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    StatsRepository statsRepository;

    @Autowired
    StatsMapper statsMapper;

    @Autowired
    StatsHistoryRepository statsHistoryRepository;

    @Override
    public StatsEntity createStats(StatsDTO statsDTO) {


        StatsHistoryEntity statsHistoryEntity = statsMapper.entityToHistory(statsRepository.findByUser(statsDTO.getUserId()));
        statsHistoryRepository.save(statsHistoryEntity);
        try {
            statsRepository.deleteByUser(statsDTO.getUserId()); //TODO Mejorable arreglar esto
        }catch (Exception e){
            System.out.println(e);
        }

        StatsEntity statsEntity = statsRepository.save(statsMapper.dtoToEntity(statsDTO));
        return statsEntity;

    }
}
