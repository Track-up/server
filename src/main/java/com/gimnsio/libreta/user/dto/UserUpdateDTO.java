package com.gimnsio.libreta.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserUpdateDTO {


    private String username;

    private String image;
    @Email
    private String email;



}
