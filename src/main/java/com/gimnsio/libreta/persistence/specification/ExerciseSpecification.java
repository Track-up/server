package com.gimnsio.libreta.persistence.specification;

import com.gimnsio.libreta.persistence.entities.ExerciseEntity;
import com.gimnsio.libreta.persistence.enums.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ExerciseSpecification implements Specification<ExerciseEntity> {

    private String name;
    private Muscle muscle;
    private Force force;
    private Level level;
    private Mechanic mechanic;
    private Equipment equipment;
    private Category category;

    public ExerciseSpecification(String name, String muscle, String force, String level, String mechanic, String equipment, String category) {
        this.name = (name!= null) ? "%" + name + "%" : null;
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

        if (name != null) {
            predicates.add(cb.like(root.get("name"), name));
        }
        if (muscle != null) {
            predicates.add(cb.equal(root.get("muscle"), muscle));
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

