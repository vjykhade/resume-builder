package com.humancloud.resume.web.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name="user_master")
public class UserRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id",columnDefinition = "varbinary(100)")
    private UUID userId;
    private String fullName;
    private String email;
    private String password;
    private String createdDate;

    private String userRole;

}
