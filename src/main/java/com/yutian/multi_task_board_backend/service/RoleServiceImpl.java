package com.yutian.multi_task_board_backend.service;

import com.yutian.multi_task_board_backend.dao.RoleRepository;
import com.yutian.multi_task_board_backend.entity.Role;

import com.yutian.multi_task_board_backend.exception.RoleNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;
    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository=roleRepository;
    }


    @Override
    public Role findByRolename(String Rolename) {
        return roleRepository.findByName(Rolename)
                .orElseThrow(()->new RoleNotFoundException(""));
    }
}
