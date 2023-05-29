package com.example.blogging.controllers;

import com.example.blogging.payloads_dto.UserDto;
import com.example.blogging.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/getUser")
    public List<UserDto> getAllUser(){
       return this.userService.getAllUsers();
    }
    @GetMapping("getUserById/{userId}")
    public UserDto getUserById(@PathVariable Integer userId){
        return this.userService.getUserById(userId);
    }

    @PostMapping("/addUser")
    public UserDto createUser(@Valid @RequestBody UserDto userDto){
       UserDto userDto1= this.userService.createUser(userDto);
    return userDto1;
    }

    @PutMapping("/addUser/{userId}")
    public UserDto updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId ){
       UserDto updateUser= this.userService.updateUser(userDto,userId);
       return updateUser;
    }

    @DeleteMapping("deleteUser/{userId}")
    public String deleteUser(@PathVariable("userId") Integer uId){
       this.userService.deleteUser(uId);
return "delete user";
    }
}
