package com.gimnsio.libreta.persistence.entities;

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

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL)
    private List<SerieEntity> exercisesOfWorkout;

    @ManyToOne
    private UserEntity worker;
    private Date date;
}
