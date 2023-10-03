package com.gimnsio.libreta.domain;

import com.gimnsio.libreta.persistence.entities.BodyPartEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Muscle {

    private Long id;
    private String name;
    private String description;
    private String image;
    private BodyPartEntity bodyPart;
}
