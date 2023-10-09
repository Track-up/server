package com.gimnsio.libreta.Mapper;


import com.gimnsio.libreta.DTO.users.*;
import com.gimnsio.libreta.persistence.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring", uses = {MeasuresMapper.class})
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

    public UserEntity userIdDTOToUserEntity(UserIdDTO userIdDTO);

    public UserIdDTO entityToIdDTO(UserEntity userEntity);
    @Mappings({
            @Mapping(target = "password", expression = "java(passwordEncoder.encode(userUpdateDTO.getPassword()))")
    })
    public void updateToEntity(UserUpdateDTO userUpdateDTO, @MappingTarget UserEntity userEntity);


}
