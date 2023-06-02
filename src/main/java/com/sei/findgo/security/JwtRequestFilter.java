package com.sei.findgo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    Logger logger = Logger.getLogger(JwtRequestFilter.class.getName());

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    private JWTUtils jwtUtils;
    @Autowired
    public void setJTWUtils(JWTUtils jwtUtils){ this.jwtUtils = jwtUtils;}


    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasLength("headerAuth") && headerAuth.startsWith("Bearer")) {
            return headerAuth.substring(7); //remove bearer prefix from token
        }
        return null;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = parseJwt(request); // extract the JWT token from the request
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) { // check if token is valid
                String username = jwtUtils.getUserNameFromJwtToken(jwt); // get the username from the token
                UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(username); //Load the user details
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication); //Set the authentication in the security context
            }
        } catch (Exception e) {
            logger.info("Cannot set user authentication");
        }
        filterChain.doFilter(request, response); //proceed with the filter chain
    }
}
