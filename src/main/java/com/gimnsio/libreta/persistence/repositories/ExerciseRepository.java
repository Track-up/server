package com.gimnsio.libreta.persistence.repositories;

import com.gimnsio.libreta.persistence.entities.ExerciseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseEntity, String>, JpaSpecificationExecutor<ExerciseEntity> {


    @Query(value = "select * from exercises where body_part = :id", nativeQuery=true)
    public Set<ExerciseEntity> findByBodyPart(Long id);

    @Query(value = "select * from exercises where name like %:name%", nativeQuery=true)
    public List<ExerciseEntity> findByName(String name, Pageable pageable);

    @Query(value = "select * from exercises where force = :force", nativeQuery=true)
    public List<ExerciseEntity> findByForce(int force, Pageable pageable);

    @Query(value = "select * from exercises where level = :level", nativeQuery=true)
    public List<ExerciseEntity> findByLevel(int level, Pageable pageable);

    @Query(value = "select * from exercises where mechanic = :mechanic", nativeQuery=true)
    public List<ExerciseEntity> findByMechanic(int mechanic, Pageable pageable);

    @Query(value = "select * from exercises where equipment = :equipment", nativeQuery=true)
    public List<ExerciseEntity> findByEquipment(int equipment, Pageable pageable);

    @Query(value = "select * from exercises where category = :category", nativeQuery=true)
    public List<ExerciseEntity> findByCategory(int category, Pageable pageable);

    @Query(value = "select * from exercises where muscle = :muscle", nativeQuery=true)
    public List<ExerciseEntity> findByMuscle(int muscle, Pageable pageable);

    @Query(value = "select * from exercises where images[1] not like '%cloudinary%'", nativeQuery = true)
    public List<ExerciseEntity> findExercisesWithoutCloudinary();




}
