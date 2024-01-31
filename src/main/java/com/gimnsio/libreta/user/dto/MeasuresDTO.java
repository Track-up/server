package com.gimnsio.libreta.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class MeasuresDTO {

    private long userId;
    private long weight;
    private long height;
    private Date date;

}
