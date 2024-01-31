package com.gimnsio.libreta.persistence.entities;

import com.gimnsio.libreta.user.persistence.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "configurations")
public class ConfigurationEntity {

    @Id
    @OneToOne
    private UserEntity user;
    private String weightUnit;
    private String language;
}
