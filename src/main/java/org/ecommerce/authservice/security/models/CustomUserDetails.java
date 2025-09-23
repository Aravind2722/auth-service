package org.ecommerce.authservice.security.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.ecommerce.authservice.models.Role;
import org.ecommerce.authservice.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonDeserialize
public class CustomUserDetails implements UserDetails {
    private Long userId;
    private String username;
    private String password;
    private List<CustomGrantedAuthority> authorities;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;

    public CustomUserDetails() {
    }
    public CustomUserDetails(User user) {
        this.userId = user.getId();
        this.username = user.getEmail();
        this.password = user.getHashedPassword();
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
//        this.authorities = user.getRoles().stream()
//                .map(CustomGrantedAuthority::new).collect(Collectors.toList());
        this.authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            this.authorities.add(new CustomGrantedAuthority(role));
        }
    }
    public Long getUserId() {
        return userId;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
