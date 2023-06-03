package com.sei.findgo.controller;
import com.sei.findgo.models.User;
import com.sei.findgo.models.request.LoginRequest;
import com.sei.findgo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "/users")
    public User getUser() {
        return userService.getCurrentUser();
    }
}
