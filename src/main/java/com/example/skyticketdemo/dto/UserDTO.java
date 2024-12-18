package com.example.skyticketdemo.dto;

import com.example.skyticketdemo.entity.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private Long userID;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
}
