package com.gimnsio.libreta.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name="body_part")
public class BodyPartEntity {
    @Id
    private Long id;
    private String name;
    private String description;
    private String image;

    public BodyPartEntity(Long id){
        this.id = id;
    }
}
