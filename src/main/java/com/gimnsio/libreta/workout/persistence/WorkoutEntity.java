package com.gimnsio.libreta.workout.persistence;

import com.gimnsio.libreta.serie.persistence.SerieEntity;
import com.gimnsio.libreta.user.persistence.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "workouts")
public class WorkoutEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SerieEntity> series;

//    private RoutineEntity routineDeDondeViene;//TODO

    @ManyToOne
    private UserEntity worker;
    private Date creationDate;
    private Date startDate;
    private Date endDate;
}
