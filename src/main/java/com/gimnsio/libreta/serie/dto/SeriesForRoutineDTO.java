package com.gimnsio.libreta.serie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SeriesForRoutineDTO {
    private Long id;
    private Long exerciseId;
    private Long series;
}
