package com.gimnsio.libreta.DTO.users;

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
