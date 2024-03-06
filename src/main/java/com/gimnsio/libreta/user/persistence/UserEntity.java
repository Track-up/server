package com.gimnsio.libreta.user.persistence;

import com.gimnsio.libreta.persistence.entities.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;


@Data
@Entity(name = "users")
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private int level;


    @Size(max = 30)
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    private String name;

    private String image;

    @Email
    @Size(max = 80)
    @Column(name = "email", unique = true, nullable = false)
    private String email;


    private String password;



    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Column(name = "created_at")
    LocalDateTime createdAt;




    public UserEntity(Long id){
        this.id = id;
    }



}
