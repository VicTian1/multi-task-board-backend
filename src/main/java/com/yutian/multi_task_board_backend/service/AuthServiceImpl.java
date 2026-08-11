package com.yutian.multi_task_board_backend.service;

import com.yutian.multi_task_board_backend.dto.JwtResponse;
import com.yutian.multi_task_board_backend.dto.UserLoginRequest;
import com.yutian.multi_task_board_backend.dto.UserResponse;
import com.yutian.multi_task_board_backend.dto.UserRegisterRequest;
import com.yutian.multi_task_board_backend.entity.Role;
import com.yutian.multi_task_board_backend.entity.User;
import com.yutian.multi_task_board_backend.exception.UserAlreadyExistsException;
import com.yutian.multi_task_board_backend.security.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService{

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder,JwtUtils jwtUtils,AuthenticationManager authenticationManager){
        this.userService=userService;
        this.roleService=roleService;
        this.passwordEncoder=passwordEncoder;
        this.jwtUtils=jwtUtils;
        this.authenticationManager=authenticationManager;
    }


    @Override
    public UserResponse register(UserRegisterRequest userRegisterRequest) {
        if(userService.existsByUsername(userRegisterRequest.getUsername())){
            throw new UserAlreadyExistsException("Username is already taken: "+ userRegisterRequest.getUsername());
        }else{
            Role userRole=roleService.findByRolename("ROLE_USER");
            User theUser = new User();
            theUser.setUsername(userRegisterRequest.getUsername());
            theUser.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
            theUser.setEnabled(true);
            theUser.setRoles(new ArrayList<Role>(List.of(userRole)));
            User user=userService.createUser(theUser);
            UserResponse response=new UserResponse();
            response.setId(user.getId());
            response.setUsername(user.getUsername());
            return response;

        }

    }

    @Override
    public JwtResponse login(UserLoginRequest userLoginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(
                userLoginRequest.getUsername(),
                userLoginRequest.getPassword()
        );

        Authentication authentication=authenticationManager.authenticate(authenticationToken);
        UserDetails userDetails=(UserDetails) authentication.getPrincipal();
        String jwtToken=jwtUtils.generateJwtToken(userDetails);
        return new JwtResponse(jwtToken);

    }


}
