package com.gimnsio.libreta.authority.service;

import com.gimnsio.libreta.authority.persistence.RoleEntity;
import com.gimnsio.libreta.authority.persistence.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleEntity findByID(long id) {
        return roleRepository.findById(id).orElse(null);
    }
}
