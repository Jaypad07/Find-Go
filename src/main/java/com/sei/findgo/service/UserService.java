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

    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, JWTUtils jwtUtils, @Lazy AuthenticationManager authenticationManager, @Lazy MyUserDetails myUserDetails) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.myUserDetails = myUserDetails;
    }

    public User registerUser(User userObject) {
        if (!userRepository.existsByEmail(userObject.getEmail())) {
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            if (userObject.getRole() == null) {
                userObject.setRole("User");
            }
            return userRepository.save(userObject);
        } else
            throw new InformationExistException("User with email address " + userObject.getEmail() + " already exists");

    }

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

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public static User getCurrentLoggedInUser() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

    public User getAUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else throw new InformationNotFoundException("User with Id " + id + " does not exist.");
    }

    public User updateUser(Long userId,  User userObject) throws InformationNotFoundException {
        if (getCurrentLoggedInUser().getRole().equals("Manager") || getCurrentLoggedInUser().getRole().equals("Admin")) {
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                User updatedUser = user.get();
                updatedUser.setUserName(userObject.getUserName());
                updatedUser.setEmail(userObject.getEmail());
                updatedUser.setPassword(userObject.getPassword());
                userRepository.save(updatedUser);
                return updatedUser;
            } else throw new InformationNotFoundException("User with Id " + userId + " does not exist.");
        } else throw new NoAuthorizationException("Insufficient privileges to update user information");
    }


    public String deleteUser(Long userId) {
        if (getCurrentLoggedInUser().getRole().equals("Admin")) {
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                userRepository.deleteById(userId);
                return "User with id " + userId + " deleted successfully.";
            } else throw new InformationNotFoundException("User with Id " + userId + " does not exist.");
        }else throw new NoAuthorizationException("Insufficient privileges to delete user information");
    }
}
