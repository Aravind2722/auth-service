package org.ecommerce.authservice.security.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.ecommerce.authservice.models.Role;
import org.springframework.security.core.GrantedAuthority;


@JsonDeserialize
public class CustomGrantedAuthority implements GrantedAuthority {
    private String authority;
    public CustomGrantedAuthority() {
    }
    public CustomGrantedAuthority(Role role) {
        this.authority = role.getRole();
    }


    @Override
    public String getAuthority() {
        return authority;
    }
}
