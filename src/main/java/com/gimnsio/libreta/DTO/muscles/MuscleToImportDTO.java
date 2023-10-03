package com.gimnsio.libreta.DTO.muscles;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MuscleToImportDTO {

    private Long id;
    private String name;
    private String description;
    private String image;
    private String bodyPartId;

}
