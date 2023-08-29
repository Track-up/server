package com.gimnsio.libreta.domain;

import com.gimnsio.libreta.DTO.users.UserDTO;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Routine {
    @Id
    private Long id;
    private String name;
    // description
    private Set<Exercise> exercises;
    private UserDTO creator;
    private Date dateOfCreation;
    private Long difficulty;
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
