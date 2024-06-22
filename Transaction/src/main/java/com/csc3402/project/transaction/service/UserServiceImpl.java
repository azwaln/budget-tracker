package com.csc3402.project.transaction.service;

import com.csc3402.project.transaction.dto.UserDto;
import com.csc3402.project.transaction.model.Role;
import com.csc3402.project.transaction.model.User;
import com.csc3402.project.transaction.repository.RoleRepository;
import com.csc3402.project.transaction.repository.UserRepository;
import com.csc3402.project.transaction.util.TbConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override

    public void saveUser(UserDto userDto){
        Role role = roleRepository.findByName(TbConstants.Roles.USER);

        if(role ==null)
            role = roleRepository.save(new Role(TbConstants.Roles.USER));

        User user = new User(userDto.getName(),userDto.getEmail(),passwordEncoder.encode(userDto.getPassword()),
                Arrays.asList(role));
        userRepository.save(user);

    }
    @Override
    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);}

}