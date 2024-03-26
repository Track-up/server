package com.gimnsio.libreta.routine.persistence;

import com.gimnsio.libreta.persistence.entities.BodyPartEntity;
import com.gimnsio.libreta.serie.persistence.SerieExampleEntity;
import com.gimnsio.libreta.user.persistence.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "routines")
public class RoutineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String image;


    // Relación muchos a uno con la clase User
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private UserEntity creator;
    @ManyToMany
    @JoinTable(
            name = "routines_body_part",
            joinColumns = @JoinColumn(name = "routine_id"),
            inverseJoinColumns = @JoinColumn(name = "body_part_id")
    )
    private List<BodyPartEntity> bodyParts;

    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SerieExampleEntity> series;
    private Date dateOfCreation;
    private Date dateOfLastEdition;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private RoutineEntity routineCoped;
    private boolean isPublic;

    public RoutineEntity(Long id) {
        this.id = id;
    }



}
