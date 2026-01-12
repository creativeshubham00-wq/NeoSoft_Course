package com.learn.security;

import com.learn.security.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

// 1. CustomUserDetails implements UserDetails
//    It acts as a bridge between User entity and Spring Security
public class CustomUserDetails implements UserDetails {

    // 2. Reference to application User entity
    private final User user;

    // 3. Constructor injection of User entity
    public CustomUserDetails(User user) {
        this.user = user;
    }

    // 4. Returns user roles/authorities
    //    Spring Security expects GrantedAuthority objects
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(
                new SimpleGrantedAuthority("ROLE_USER")
        );
    }

    // 5. Returns encrypted password from database
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // 6. Returns username from database
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 7. Indicates whether the account is expired
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 8. Indicates whether the account is locked
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 9. Indicates whether credentials are expired
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 10. Indicates whether the account is enabled
    @Override
    public boolean isEnabled() {
        return true;
    }
}
