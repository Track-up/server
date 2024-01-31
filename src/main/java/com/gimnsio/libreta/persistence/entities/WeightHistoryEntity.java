package com.gimnsio.libreta.persistence.entities;

import com.gimnsio.libreta.user.persistence.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity(name = "weight_history")
public class WeightHistoryEntity {

    @Id
    private Long id;
    @ManyToOne
    private UserEntity user;
    private long weight;
    private Date date;
}
