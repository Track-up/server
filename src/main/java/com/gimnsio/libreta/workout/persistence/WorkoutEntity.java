package com.gimnsio.libreta.workout.persistence;

import com.gimnsio.libreta.serie.persistence.SerieEntity;
import com.gimnsio.libreta.persistence.entities.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "workouts")
public class WorkoutEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SerieEntity> series;

    @ManyToOne
    private UserEntity worker;
    private Date creationDate;
    private Date startDate;
    private Date endDate;
}
