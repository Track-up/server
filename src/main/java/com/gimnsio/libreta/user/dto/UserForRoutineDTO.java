package com.gimnsio.libreta.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserForRoutineDTO {
    private long id;
    private String username;
    private String image;
    private int level;
}
