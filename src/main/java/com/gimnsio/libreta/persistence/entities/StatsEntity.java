package com.gimnsio.libreta.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Entity(name = "stats")
@NoArgsConstructor
@Data
public class StatsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private long weight;
    private long height;


    private Date date;


}
