package com.learn.security.service;

import com.learn.security.CustomUserDetails;
import com.learn.security.entity.User;
import com.learn.security.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

// 1. Marks this class as a Spring Service component
@Service
public class CustomUserDetailService implements UserDetailsService {

    // 2. Repository used to fetch user details from database
    private final UserRepository userRepository;

    // 3. Constructor injection of UserRepository
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 4. This method is called by Spring Security during authentication
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 5. Fetch user from database using username
        User user = userRepository.findByUsername(username);

        // 6. If user does not exist, throw UsernameNotFoundException
        if (Objects.isNull(user)) {
            System.out.println("User not available");
            throw new UsernameNotFoundException("User not found");
        }

        // 7. Wrap User entity inside CustomUserDetails
        //    and return it to Spring Security
        return new CustomUserDetails(user);
    }
}
