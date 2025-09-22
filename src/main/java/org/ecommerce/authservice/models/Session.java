package org.ecommerce.authservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Session extends BaseModel {
    @ManyToOne
    private User user;
    private String token;
    private Date expiringAt;
    @Enumerated(EnumType.STRING)
    private SessionStatus sessionStatus;
}
