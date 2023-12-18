//package com.gimnsio.libreta.services;
//
//
//import com.gimnsio.libreta.DTO.muscles.MuscleToImportDTO;
//import com.gimnsio.libreta.domain.Muscle;
//import com.gimnsio.libreta.persistence.entities.BodyPartEntity;
//import com.gimnsio.libreta.persistence.entities.MuscleEntity;
//import org.springframework.data.domain.Pageable;
//
//import java.util.List;
//import java.util.Set;
//
//public interface MuscleService {
//
//    public List<Muscle> getAllMuscles(Pageable pageable);
//
//    public Muscle getMuscleById(Long id);
//
//    public Muscle updateMuscle(Long id, Muscle muscle);
//
//    public Muscle createMuscle(Muscle muscle);
//
//    public void deleteMuscle(Long id);
//
//    public Set<MuscleEntity> createMuscles(Set<MuscleToImportDTO> muscles);
//
//    public Set<BodyPartEntity> createBodyParts(Set<BodyPartEntity> bodyPart);
//}
