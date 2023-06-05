package com.sei.findgo.security;

import com.sei.findgo.models.User;
import com.sei.findgo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * UserDetailsService implementation for loading user details.
 *
 * This class implements the {@link UserDetailsService} interface to provide user details loading functionality.
 * It interacts with the {@link UserService} to retrieve user information based on the provided email.
 * The retrieved user details are encapsulated in a {@link MyUserDetails} object for authentication and authorization.
 */

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserService userService;

    /**
     * Sets the UserService dependency for retrieving user information.
     *
     * @param userService the UserService instance to be set
     */
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Loads user details by the provided email.
     *
     * @param email the email of the user
     * @return the UserDetails containing the user details
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findUserByEmail(email);
        return new MyUserDetails(user);
    }
}
