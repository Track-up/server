package com.gimnsio.libreta.services;

import com.gimnsio.libreta.users.UserBasicsDTO;
import com.gimnsio.libreta.users.UserDTO;
import com.gimnsio.libreta.users.UserRegistryDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Set;


public interface UsersService {

    public Set<UserBasicsDTO> getAllUsers(Pageable pageable);
    public UserDTO getUserById(long id);
    public ResponseEntity<?> createUser(UserRegistryDTO userRegistryDTO);
    public ResponseEntity<?> updateUser(long id, UserDTO userDTO);
    public ResponseEntity<?> deleteUser(long id);

}
