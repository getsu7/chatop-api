package com.getsu.chatop.application.auth.models;

import lombok.Getter;

@Getter
public class RegisterRequest {
    private String email;
    private String password;
    private String name;
}
