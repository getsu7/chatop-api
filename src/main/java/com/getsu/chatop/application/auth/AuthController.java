package com.getsu.chatop.application.auth;

import com.getsu.chatop.application.auth.models.LoginRequest;
import com.getsu.chatop.application.auth.models.RegisterRequest;
import com.getsu.chatop.application.auth.models.TokenResponse;
import com.getsu.chatop.domain.service.UserService;
import com.getsu.chatop.domain.service.JWTService;
import com.getsu.chatop.infrastructure.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
class AuthController {

    private UserService userService;

    private JWTService jwtService;

    AuthController(UserService userService, JWTService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("register")
    ResponseEntity<TokenResponse> register(@RequestBody RegisterRequest registerRequest) {
        User user = userService.registerUser(registerRequest);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                null
        );

        String token = jwtService.generateToken(authentication);

        return ResponseEntity.ok(new TokenResponse(token));
    }

    @PostMapping("login")
    ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {

        User user = userService.loginUser(loginRequest);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                null
        );

        String token = jwtService.generateToken(authentication);
        return ResponseEntity.ok(new TokenResponse(token));
    }
}
