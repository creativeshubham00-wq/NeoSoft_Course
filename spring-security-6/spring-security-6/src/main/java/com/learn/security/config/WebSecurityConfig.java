package com.learn.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// 1. This class is responsible for configuring Spring Security
@Configuration
// 2. Enables Spring Security for web applications
@EnableWebSecurity
public class WebSecurityConfig {

    // 3. UserDetailsService is injected (can be in-memory or DB based)
    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    // 4. Constructor injection of UserDetailsService
    public WebSecurityConfig(UserDetailsService userDetailsService, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    // 5. Defines the SecurityFilterChain bean
    // 6. All incoming HTTP requests pass through this filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity

                // 7. Disables CSRF protection
                //    (commonly disabled for REST APIs)
                .csrf(csrf -> csrf.disable())

                // 8. Defines authorization rules for HTTP requests
                .authorizeHttpRequests(request ->

                        // 9. Public endpoints (no authentication required)
                        request.requestMatchers("/register", "/login").permitAll()

                                // 10. All other requests must be authenticated
                                .anyRequest().authenticated()
                )

                // 11. Enables HTTP Basic authentication
                //     (useful for Postman and testing)
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // 12. Builds and returns the SecurityFilterChain
        return httpSecurity.build();
    }

    // 13. Password encoder bean using BCrypt algorithm
    //     (recommended for production)
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(14);
    }

    // 14. AuthenticationProvider defines how authentication is performed
    @Bean
    public AuthenticationProvider authenticationProvider() {

        // 15. DaoAuthenticationProvider fetches user data from UserDetailsService
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        // 16. Sets custom UserDetailsService
        provider.setUserDetailsService(userDetailsService);

        // 17. Sets BCrypt password encoder
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }
}
