package com.gimnsio.libreta.services;

import com.gimnsio.libreta.DTO.users.UserBasicsDTO;
import com.gimnsio.libreta.DTO.users.UserDTO;
import com.gimnsio.libreta.DTO.users.UserRegistryDTO;
import com.gimnsio.libreta.DTO.users.UserUpdateDTO;
import com.gimnsio.libreta.persistence.entities.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;


public interface UserService {

    public Set<UserBasicsDTO> getAllUsers(Pageable pageable);
    public UserDTO getUserById(long id);
    public UserEntity getUserEntityById(long id);
    public ResponseEntity<?> createUser(UserRegistryDTO userRegistryDTO);
    public UserUpdateDTO updateUser(Long id, UserUpdateDTO userDTO);
    public void deleteUser(long id);

    public List<UserEntity> getAllUsersEntities(Pageable pageable);

//    public MeasuresDTO updateUserStats(Long id, MeasuresDTO measuresDTO);
}
