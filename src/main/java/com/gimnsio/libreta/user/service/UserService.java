package com.gimnsio.libreta.user.service;

import com.gimnsio.libreta.user.dto.UserBasicsDTO;
import com.gimnsio.libreta.user.dto.UserDTO;
import com.gimnsio.libreta.user.dto.UserRegistryDTO;
import com.gimnsio.libreta.user.dto.UserUpdateDTO;
import com.gimnsio.libreta.user.persistence.UserEntity;
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
