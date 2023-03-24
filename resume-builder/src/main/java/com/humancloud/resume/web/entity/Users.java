package com.humancloud.resume.web.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name="users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id",columnDefinition = "varbinary(100)")
    private UUID userId;
    private String fullName;
    private String email;
    private String password;
    private String createdDate;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="user_role", joinColumns = @JoinColumn(name = "email", referencedColumnName = "email"),
    inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "role_id"))
    private Set<Role> roles;

}
