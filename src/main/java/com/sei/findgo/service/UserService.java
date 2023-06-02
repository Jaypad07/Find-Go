package com.sei.findgo.service;

import com.sei.findgo.exceptions.InformationExistException;
import com.sei.findgo.models.User;
import com.sei.findgo.repository.UserRepository;
import com.sei.findgo.security.JWTUtils;
import com.sei.findgo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JWTUtils jwUtils;
    private AuthenticationManager authenticationManager;
    private MyUserDetails myUserDetails;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User userObject){
        if (!userRepository.existsByEmail(userObject.getEmail())) {
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            userObject.setRole("ROLE_USER");
            return userRepository.save(userObject);
        }else throw new InformationExistException("User with email address " + userObject.getEmail() + " already exists");

    }
    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

}
