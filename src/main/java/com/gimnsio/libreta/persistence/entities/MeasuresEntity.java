package com.gimnsio.libreta.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Entity(name = "measures")
@NoArgsConstructor
@Data
public class MeasuresEntity {

    @Id
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private long weight;
    private long height;


    private Date date;


}
