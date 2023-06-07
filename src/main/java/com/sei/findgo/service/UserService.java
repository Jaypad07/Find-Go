package com.sei.findgo.service;

import com.sei.findgo.exceptions.InformationExistException;
import com.sei.findgo.exceptions.InformationNotFoundException;
import com.sei.findgo.exceptions.NoAuthorizationException;
import com.sei.findgo.models.User;
import com.sei.findgo.models.request.LoginRequest;
import com.sei.findgo.models.response.LoginResponse;
import com.sei.findgo.repository.UserRepository;
import com.sei.findgo.security.JWTUtils;
import com.sei.findgo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JWTUtils jwUtils;
    private AuthenticationManager authenticationManager;
    private MyUserDetails myUserDetails;

    /**
     * Service class for managing users.
     *
     * This class provides methods to handle user registration, login, retrieval, update, and deletion.
     * It interacts with the {@link UserRepository} to access and manipulate user data securely.
     * The service methods enforce business logic and authorization checks for user operations.
     */

    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, JWTUtils jwtUtils, @Lazy AuthenticationManager authenticationManager, @Lazy MyUserDetails myUserDetails) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.myUserDetails = myUserDetails;
    }

    /**
     * Registers a new user.
     *
     * @param userObject The user object to register.
     * @return The registered user.
     * @throws InformationExistException If a user with the same email address already exists.
     */
    public User registerUser(User userObject) {
        if (!userRepository.existsByEmailIgnoreCase(userObject.getEmail())) {
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            if (userObject.getRole() == null) {
                userObject.setRole("User");
            }
            return userRepository.save(userObject);
        } else {
            throw new InformationExistException("User with email address " + userObject.getEmail() + " already exists");
        }
    }

    /**
     * Logs in a user.
     *
     * @param loginRequest The login request containing the user's email and password.
     * @return A response entity containing the JWT token if the login is successful, or an error message if the login fails.
     */
    public ResponseEntity<?> loginUser(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            myUserDetails = (MyUserDetails) authentication.getPrincipal();
            final String JWT = jwUtils.generateJwtToken(myUserDetails);
            return ResponseEntity.ok(new LoginResponse(JWT));
        } catch (Exception e) {
            return ResponseEntity.ok(new LoginResponse("Username or password is incorrect"));
        }
    }

    /**
     * Retrieves a user by email.
     *
     * @param email The email of the user to retrieve.
     * @return The user with the specified email.
     */
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    /**
     * Retrieves all users.
     *
     * @return A list of all users.
     * @throws NoAuthorizationException If the current logged-in user does not have sufficient privileges to view all users.
     */
    public List<User> getAllUsers() {
        if (getCurrentLoggedInUser().getRole().equals("Admin")) {
            return userRepository.findAll();
        } else {
            throw new NoAuthorizationException("Insufficient privileges to view all users");
        }
    }

    /**
     * Retrieves the current logged-in user.
     *
     * @return The current logged-in user.
     */
    public static User getCurrentLoggedInUser() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The user with the specified ID.
     * @throws InformationNotFoundException If the user with the specified ID does not exist.
     * @throws NoAuthorizationException If the current logged-in user does not have sufficient privileges to view the user.
     */
    public User getAUserById(Long id) {
        if (getCurrentLoggedInUser().getRole().equals("Admin")) {
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
                return user.get();
            } else {
                throw new InformationNotFoundException("User with Id " + id + " does not exist.");
            }
        } else {
            throw new NoAuthorizationException("Insufficient privileges to view user information");
        }
    }

    /**
     * Updates a user's information.
     *
     * @param userId The ID of the user to update.
     * @param userObject The updated user object.
     * @return The updated user.
     * @throws InformationNotFoundException If the user with the specified ID does not exist.
     * @throws NoAuthorizationException If the current logged-in user does not have sufficient privileges to update user information.
     */
    public User updateUser(Long userId, User userObject) throws InformationNotFoundException {
        if (getCurrentLoggedInUser().getRole().equals("Manager") || getCurrentLoggedInUser().getRole().equals("Admin")) {
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                User updatedUser = user.get();
                updatedUser.setUserName(userObject.getUserName());
                updatedUser.setEmail(userObject.getEmail());
                updatedUser.setPassword(passwordEncoder.encode(userObject.getPassword()));
                if (getCurrentLoggedInUser().getRole().equalsIgnoreCase("Admin")) {
                    updatedUser.setRole(userObject.getRole());
                    userRepository.save(updatedUser);
                } else {
                    userRepository.save(updatedUser);
                }
                return updatedUser;
            } else {
                throw new InformationNotFoundException("User with Id " + userId + " does not exist.");
            }
        } else {
            throw new NoAuthorizationException("Insufficient privileges to update user information");
        }
    }

    /**
     * Deletes a user.
     *
     * @param userId The ID of the user to delete.
     * @return The deleted user.
     * @throws InformationNotFoundException If the user with the specified ID does not exist.
     * @throws NoAuthorizationException If the current logged-in user does not have sufficient privileges to delete user information.
     */
    public User deleteUser(Long userId) {
        if (getCurrentLoggedInUser().getRole().equals("Admin")) {
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                userRepository.deleteById(userId);
                return user.get();
            } else {
                throw new InformationNotFoundException("User with Id " + userId + " does not exist.");
            }
        } else {
            throw new NoAuthorizationException("Insufficient privileges to delete user information");
        }
    }
}
