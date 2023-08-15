package com.gimnsio.libreta.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exercise {
    private Long id;
    private String name;
    private String image;
    private String video;
    private String description;
    private Set<Muscle> muscles;
    private Date dateOfCreation;
    private Long difficult;
    private Long likes;

}
