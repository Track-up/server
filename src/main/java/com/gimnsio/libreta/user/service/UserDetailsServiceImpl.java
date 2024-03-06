package com.gimnsio.libreta.user.service;

import com.gimnsio.libreta.user.dto.UserIdDTO;
import com.gimnsio.libreta.user.mapper.UserMapper;
import com.gimnsio.libreta.user.persistence.UserEntity;
import com.gimnsio.libreta.user.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));

        Collection<? extends GrantedAuthority> authorities = userEntity.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.name())))
                .collect(Collectors.toSet());

        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }


    public UserIdDTO getUserIdDTOByUsername(String username) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(username);
        if (userEntityOptional.isPresent()){
            return userMapper.entityToIdDTO(userEntityOptional.get());
        }
        return null;
    }

}
