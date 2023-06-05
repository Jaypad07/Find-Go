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

    /**
     * Sets the user service.
     *
     * @param userService The user service to be set.
     */
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Registers a new user.
     *
     * @param userObject The user object to be registered.
     * @return The registered user.
     */
    @PostMapping(path = "/users/register")
    public User registerUser(@RequestBody User userObject) {
        return userService.registerUser(userObject);
    }

    /**
     * Logs in a user with the provided credentials.
     *
     * @param loginRequest The login request object containing the user's credentials.
     * @return A ResponseEntity with the login response.
     */
    @PostMapping(path = "/users/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        return userService.loginUser(loginRequest);
    }

    /**
     * Retrieves a list of all users.
     *
     * @return A list of all users.
     */
    @GetMapping("/auth/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId The ID of the user.
     * @return The user with the specified ID.
     */
    @GetMapping(path = "/auth/users/{userId}")
    public User getAUserById(@PathVariable Long userId) {
        return userService.getAUserById(userId);
    }

    /**
     * Updates a user with the specified ID.
     *
     * @param userId The ID of the user to be updated.
     * @param userObject The updated user object.
     * @return The updated user.
     */
    @PutMapping(path = "/auth/users/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User userObject) {
        return userService.updateUser(userId, userObject);
    }

    /**
     * Deletes a user with the specified ID.
     *
     * @param userId The ID of the user to be deleted.
     * @return The deleted user.
     */
    @DeleteMapping(path = "/auth/users/{userId}")
    public User deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }
}

