package com.gimnsio.libreta.persistence.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gimnsio.libreta.user.persistence.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Entity(name = "measures")
@NoArgsConstructor
@Data
public class MeasuresEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserEntity user;

    private long weight;
    private long height;


    private Date date;


}
