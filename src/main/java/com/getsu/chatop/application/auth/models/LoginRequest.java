package com.getsu.chatop.application.auth.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginRequest {
    private String email;
    private String password;
    private String name;
}
