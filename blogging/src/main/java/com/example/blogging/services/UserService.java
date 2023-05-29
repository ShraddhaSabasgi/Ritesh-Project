package com.example.blogging.services;

import com.example.blogging.entity.User;
import com.example.blogging.payloads_dto.UserDto;

import java.util.List;

public interface UserService {


    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,Integer userID);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);


}
