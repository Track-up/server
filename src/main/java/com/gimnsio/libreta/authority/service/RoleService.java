package com.gimnsio.libreta.authority.service;

import com.gimnsio.libreta.authority.persistence.RoleEntity;
import org.springframework.stereotype.Service;


public interface RoleService {

    RoleEntity findByID(long id);
}
