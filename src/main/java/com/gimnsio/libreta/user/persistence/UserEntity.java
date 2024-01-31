package com.gimnsio.libreta.user.persistence;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gimnsio.libreta.persistence.entities.RoleEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;


@Data
@Entity(name = "users")
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private int level;

    @NotBlank
    @Size(max = 30)
    @Column(name = "username", unique = true, nullable = false)
    private String username;



    private String image;

    @Email
    @Size(max = 80)
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotBlank
    private String password;


    @ManyToMany(fetch = FetchType.EAGER, targetEntity = RoleEntity.class, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles;


    private Date dateOfCreation;




    public UserEntity(Long id){
        this.id = id;
    }

}
