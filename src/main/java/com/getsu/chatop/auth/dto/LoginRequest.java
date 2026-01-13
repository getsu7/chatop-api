package com.getsu.chatop.auth.dto;

import lombok.Builder;

@Builder
public record LoginRequest(String email, String password) {
}
