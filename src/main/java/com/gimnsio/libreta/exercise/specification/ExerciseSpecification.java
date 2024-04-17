package com.gimnsio.libreta.exercise.specification;

import com.gimnsio.libreta.exercise.persistence.ExerciseEntity;
import com.gimnsio.libreta.persistence.enums.*;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ExerciseSpecification implements Specification<ExerciseEntity> {


    private String name;

    private String query;

    private String nameEs;
    private Muscle muscle;
    private Force force;
    private Level level;
    private Mechanic mechanic;
    private Equipment equipment;
    private Category category;

    public ExerciseSpecification(String query, String name, String nameEs, String muscle, String force, String level, String mechanic, String equipment, String category) {
        this.query = (query != null) ? query : null;
        this.name = (name != null) ? "%" + name + "%" : null;
        this.nameEs = (nameEs != null) ? "%" + nameEs + "%" : null;
        this.muscle = (muscle != null) ? Muscle.valueOf(muscle) : null;
        this.force = (force != null) ? Force.valueOf(force) : null;
        this.level = (level != null) ? Level.valueOf(level) : null;
        this.mechanic = (mechanic != null) ? Mechanic.valueOf(mechanic) : null;
        this.equipment = (equipment != null) ? Equipment.valueOf(equipment) : null;
        this.category = (category != null) ? Category.valueOf(category) : null;
    }

    @Override
    public Predicate toPredicate(Root<ExerciseEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (this.query != null) {
            // Construir la expresión para la función similarity
            Expression<Float> similarityFunction = cb.function(
                    "similarity",
                    Float.class,
                    root.get("nameEs"),
                    cb.literal(this.query)
            );

            // Agregar la condición para que la similitud sea mayor que 0.5
            predicates.add(cb.greaterThan(similarityFunction, 0f));
        }

        if (name != null) {
            Expression<String> upperName = cb.upper(root.get("name"));
            predicates.add(cb.like(upperName, name.toUpperCase()));
        }
        if (nameEs != null) {
            Expression<String> upperName = cb.upper(root.get("nameEs"));
            predicates.add(cb.like(upperName, nameEs.toUpperCase()));
        }
        if (muscle != null) {
            List<Muscle> muscles = new ArrayList<>();
            muscles.add(muscle);
            predicates.add(cb.equal(root.get("primaryMuscles"), muscles));
        }
        if (force != null) {
            predicates.add(cb.equal(root.get("force"), force));
        }
        if (level != null) {
            predicates.add(cb.equal(root.get("level"), level));
        }
        if (mechanic != null) {
            predicates.add(cb.equal(root.get("mechanic"), mechanic));
        }
        if (equipment != null) {
            predicates.add(cb.equal(root.get("equipment"), equipment));
        }
        if (category != null) {
            predicates.add(cb.equal(root.get("category"), category));
        }

        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}

