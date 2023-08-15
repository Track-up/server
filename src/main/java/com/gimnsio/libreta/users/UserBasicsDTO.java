package com.gimnsio.libreta.users;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class UserBasicsDTO {

    private Long id;
    private String username;
    private String email;

}
