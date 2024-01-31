package com.gimnsio.libreta.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisteredDTO {

    private Long id;
    private String username;
    private String Token;

}
