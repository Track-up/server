package com.gimnsio.libreta.services;

import com.gimnsio.libreta.DTO.users.UserBasicsDTO;
import com.gimnsio.libreta.DTO.users.UserDTO;
import com.gimnsio.libreta.DTO.users.UserRegistryDTO;
import com.gimnsio.libreta.persistence.entities.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Set;


public interface UserService {

    public Set<UserBasicsDTO> getAllUsers(Pageable pageable);
    public UserDTO getUserById(long id);
    public UserEntity getUserEntityById(long id);
    public ResponseEntity<?> createUser(UserRegistryDTO userRegistryDTO);
    public ResponseEntity<?> updateUser(long id, UserDTO userDTO);
    public ResponseEntity<?> deleteUser(long id);

}
