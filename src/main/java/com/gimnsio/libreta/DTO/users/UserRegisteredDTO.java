package com.gimnsio.libreta.DTO.users;

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
