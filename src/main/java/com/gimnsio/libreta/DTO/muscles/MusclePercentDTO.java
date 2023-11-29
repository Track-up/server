package com.gimnsio.libreta.DTO.muscles;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MusclePercentDTO {
    private Long id;
    private String name;
    private Double percent;
}
