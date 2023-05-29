package com.example.blogging.ServiceImpl;

import com.example.blogging.entity.User;
import com.example.blogging.payloads_dto.UserDto;
import com.example.blogging.repository.UserRepository;
import com.example.blogging.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
            User user=this.dtoToUser(userDto);
            User savedUser=this.userRepository.save(user);
            return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userID) {
        User user=this.userRepository.findById(userID).orElseThrow(() -> new RuntimeException());

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

       User updateUser= this.userRepository.save(user);
       UserDto userDto1=this.userToDto(updateUser);
       return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user=this.userRepository.findById(userId).orElseThrow(() -> new RuntimeException());
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
       List<User> users= this.userRepository.findAll();
      List<UserDto> userDtos= users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=this.userRepository.findById(userId).orElseThrow(() -> new RuntimeException());
        this.userRepository.delete(user);
    }

    public User dtoToUser(UserDto userDto){
        User user=this.modelMapper.map(userDto,User.class);
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        return user;
    }

    public UserDto userToDto(User user){
        UserDto userDto=this.modelMapper.map(user,UserDto.class);
//        UserDto userDto=new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return userDto;
    }
}
