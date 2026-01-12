package com.learn.security.service;

import com.learn.security.entity.User;
import com.learn.security.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

// 1. Marks this class as a Spring Service component
@Service
public class UserService {

    // 2. Repository used to perform database operations on User entity
    private final UserRepository userRepository;

    // 3. BCryptPasswordEncoder used to securely hash passwords
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    // 4. Constructor injection of required dependencies
    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    // 5. Registers a new user in the system
    public User register(User user) {

        // 6. Encrypts the raw password before saving to database
        user.setPassword(
                bCryptPasswordEncoder.encode(user.getPassword())
        );

        // 7. Saves the user entity into database
        return userRepository.save(user);
    }

    public String verify(User user) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );

        if (authenticate.isAuthenticated()) {
            return jwtService.generateToken(user); // token
        }

        return "failure";
    }
}
