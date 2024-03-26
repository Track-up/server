package com.gimnsio.libreta.user.service;

import com.gimnsio.libreta.user.dto.UserIdDTO;
import com.gimnsio.libreta.user.mapper.UserMapper;
import com.gimnsio.libreta.user.persistence.UserEntity;
import com.gimnsio.libreta.user.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity;
        if(username.contains("@")){
           userEntity = userRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("El correo " + username + " no existe."));
        }else{
            userEntity = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));
        }


        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userEntity.getRoles()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

        userEntity.getRoles().stream()
                .flatMap(role -> role.getPermissionList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));


        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.isAccountNoExpired(),
                userEntity.isCredentialNoExpired(),
                userEntity.isAccountNoLocked(),
                authorityList);
    }

        //no
//        Collection<? extends GrantedAuthority> authorities = userEntity.getRoles()
//                .stream()
//                .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.name())))
//                .collect(Collectors.toSet());
//
//        return new User(userEntity.getUsername(),
//                userEntity.getPassword(),
//                true,
//                true,
//                true,
//                true,
//                authorities);



    public UserIdDTO getUserIdDTOByUsername(String username) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(username);
        if (userEntityOptional.isPresent()){
            return userMapper.entityToIdDTO(userEntityOptional.get());
        }
        return null;
    }

}
