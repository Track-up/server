package com.gimnsio.libreta.Mapper;


import com.gimnsio.libreta.persistence.entities.UserEntity;
import com.gimnsio.libreta.DTO.users.UserBasicsDTO;
import com.gimnsio.libreta.DTO.users.UserDTO;
import com.gimnsio.libreta.DTO.users.UserRegisteredDTO;
import com.gimnsio.libreta.DTO.users.UserRegistryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserMapper {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public UserDTO mapUserDTO(UserEntity userEntity);

    public UserRegisteredDTO userEntityToUserRegisteredDTO(UserEntity userEntity);

    @Mappings({
            @Mapping(target = "password", expression = "java(passwordEncoder.encode(userRegistryDTO.getPassword()))")
//            @Mapping(target = "roles", expression = "java()")
    })
    public UserEntity userRegistryDTOToUserEntity(UserRegistryDTO userRegistryDTO);

    public UserBasicsDTO userEntityToUserBasicsDTO(UserEntity userEntity);


}
