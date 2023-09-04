package com.gimnsio.libreta.DTO.routines;

import com.gimnsio.libreta.DTO.exercises.ExerciseForRoutineDTO;
import com.gimnsio.libreta.DTO.users.UserBasicsDTO;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoutineDTO {
    @Id
    private Long id;
    private String name;
    // description
    private List<ExerciseForRoutineDTO> exercises;
    private UserBasicsDTO creator;
    private Date dateOfCreation;
    private Long durationMinutes;
    private boolean isPublic;// Puede ser pública o privada.

    // Add , etc.

    // Yo soy user1, en mi app veo varias rutinas de default, las puedo hacer o
    // crear mis propias rutinas. Al crear una rutina puedo decir de cuanto tiempo
    // es... , te da unos puntos al acabar, se hacen de tiempo X y Y.
    // Informas de X días por semana. Si no lo completas, te quita puntos.

    // Una rutina tiene workouts, un workout tiene series, una serie tiene
    // ejercicios.

    // Los ejercicios se pueden crear, pero tienen que estar validados por un admin,
    // puedes usarlos mientras no estén validados pero solo tu los ves, esa rutina
    // no la puede ver nadie más.

    // Muchos usuarios hacen muchas rutinas, en esa relación se guarda cuando empezo
    // y termino la rutina.

}
