package com.gimnsio.libreta.Mapper;

import com.gimnsio.libreta.user.dto.MeasuresDTO;
import com.gimnsio.libreta.user.dto.MeasuresForUserDTO;
import com.gimnsio.libreta.persistence.entities.MeasuresEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MeasuresMapper {


    public MeasuresEntity dtoToEntity (MeasuresDTO measuresDTO);

//    public MeasuresHistoryEntity entityToHistory(MeasuresEntity measuresEntity);

    public MeasuresDTO entityToDto(MeasuresEntity measuresEntity);

    public MeasuresForUserDTO entityToForUser(MeasuresEntity measuresEntity);

}
