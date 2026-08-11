package com.yutian.multi_task_board_backend.service;

import com.yutian.multi_task_board_backend.dao.UserRepository;
import com.yutian.multi_task_board_backend.entity.Role;
import com.yutian.multi_task_board_backend.entity.User;
import com.yutian.multi_task_board_backend.exception.UserNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public User findByUsername(String username) {

        return userRepository.findByUsername(username)
                .orElseThrow(()->new UserNotFoundException("User not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        try{
            User user=findByUsername(username);
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    user.isEnabled(),
                    true,
                    true,
                    true,
                    mapRolesToAuthorities(user.getRoles()));
        }catch(UserNotFoundException e){
            throw new UsernameNotFoundException("Invalid username or password.");
        }

    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }



    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
}
