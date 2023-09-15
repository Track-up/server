package com.gimnsio.libreta.DTO.users;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class StatsDTO {

    private long userId;
    private long weight;
    private long height;
    private Date date;

}
