package com.sei.findgo.controller;

import com.sei.findgo.exceptions.InformationExistException;
import com.sei.findgo.exceptions.NoAuthorizationException;
import com.sei.findgo.models.Product;
import com.sei.findgo.models.User;
import com.sei.findgo.models.request.LoginRequest;
import com.sei.findgo.repository.UserRepository;
import com.sei.findgo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * POST: endpoint http://localhost:8080/api/auth/users/register
     * @param userObject
     * @return model User
     */
    @PostMapping (path = "/auth/users/register")
    public User registerUser(@RequestBody User userObject) {
        return userService.registerUser(userObject);
    }

    /**
     * POST: endpoint http://localhost:8080/api/auth/users/login
     * @param loginRequest
     * @return ResponseEntity that allows developer to access status codes, headers, and response body.
     */
    @PostMapping(path="/auth/users/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
        return userService.loginUser(loginRequest);
    }

    @GetMapping(path = "/users")
    public User getUser() {
        return userService.getCurrentUser();
    }
}
