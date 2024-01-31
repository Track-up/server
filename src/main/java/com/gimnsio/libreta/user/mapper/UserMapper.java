package com.gimnsio.libreta.user.mapper;


import com.gimnsio.libreta.Mapper.MeasuresMapper;
import com.gimnsio.libreta.user.dto.*;
import com.gimnsio.libreta.user.persistence.UserEntity;
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
    public void updateToEntity(UserUpdateDTO userUpdateDTO, @MappingTarget UserEntity userEntity);


}
