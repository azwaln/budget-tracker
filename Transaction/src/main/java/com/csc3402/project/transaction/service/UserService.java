package com.csc3402.project.transaction.service;

import com.csc3402.project.transaction.dto.UserDto;
import com.csc3402.project.transaction.model.User;

public interface UserService {

    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
}