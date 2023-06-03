package com.sei.findgo.controller;
import com.sei.findgo.models.User;
import com.sei.findgo.models.request.LoginRequest;
import com.sei.findgo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping (path = "/users/register")
    public User registerUser(@RequestBody User userObject) {
        return userService.registerUser(userObject);
    }

    @PostMapping(path="/users/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
        return userService.loginUser(loginRequest);
    }

    @GetMapping("/auth/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/auth/users/{userId}")
    public User getAUserById(@PathVariable Long userId) {
        return userService.getAUserById(userId);
    }

    @PutMapping(path = "/auth/users/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User userObject) {
        return userService.updateUser(userId, userObject);
    }

    @DeleteMapping(path = "/auth/users/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}
