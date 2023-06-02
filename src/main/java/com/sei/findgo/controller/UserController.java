package com.sei.findgo.controller;

import com.sei.findgo.exceptions.InformationExistException;
import com.sei.findgo.models.User;
import com.sei.findgo.models.request.LoginRequest;
import com.sei.findgo.repository.UserRepository;
import com.sei.findgo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping (path = "/auth/users/register")
    public User registerUser(@RequestBody User userObject) {
        return userService.registerUser(userObject);
    }

    @PostMapping(path="/auth/users/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
        return userService.loginUser(loginRequest);
    }

    public void getUser() {
        Optional<User> user = userRepository.findById(1L);
    }
}
