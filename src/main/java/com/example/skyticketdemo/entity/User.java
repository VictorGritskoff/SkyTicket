package com.example.skyticketdemo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @Column(nullable = false, length = 255)
    private String firstName;

    @Column(nullable = false, length = 255)
    private String lastName;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.USER;

}
