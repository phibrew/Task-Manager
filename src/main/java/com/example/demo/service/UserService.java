package com.example.demo.service;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegistrationDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    //final ensure that object initialized once only
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public UserDto registerUser(RegistrationDto registrationDto){
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        return saveUser(user);
    }
    public UserDto saveUser(User user){
        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }
    public List<UserDto> findAllUsers(){
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for(User user : users){
            userDtos.add(convertToDto(user));
        }
        return userDtos;
    }
    public UserDto loginUser(LoginDto loginDto){
        User user = userRepository.findByUsername(loginDto.getUsername())
                .orElseThrow(()->new IllegalArgumentException("Input not found"));

        if(!passwordEncoder.matches(loginDto.getPassword(),user.getPassword())){
            throw new RuntimeException("Wrong password");
        }
        return convertToDto(user);
    }
    private UserDto convertToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        return userDto;
    }
}
