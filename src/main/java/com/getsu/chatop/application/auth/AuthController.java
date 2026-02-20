package com.getsu.chatop.application.auth;

import com.getsu.chatop.application.auth.models.LoginRequest;
import com.getsu.chatop.application.auth.models.RegisterRequest;
import com.getsu.chatop.application.auth.models.TokenResponse;
import com.getsu.chatop.application.auth.models.UserResponse;
import com.getsu.chatop.domain.service.UserService;
import com.getsu.chatop.domain.service.JWTService;
import com.getsu.chatop.infrastructure.models.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
class AuthController {

    private final UserService userService;

    private final JWTService jwtService;

    AuthController(UserService userService, JWTService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("register")
    @Operation(summary = "Inscription d'un nouvel utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur enregistré avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
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
    @Operation(summary = "Connexion d'un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Connexion réussie"),
            @ApiResponse(responseCode = "401", description = "Identifiants invalides")
    })
    ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {

        User user = userService.loginUser(loginRequest);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                null
        );

        String token = jwtService.generateToken(authentication);
        return ResponseEntity.ok(new TokenResponse(token));
    }

    @GetMapping("/me")
    @Operation(summary = "Récupérer les informations de l'utilisateur authentifié")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informations récupérées avec succès"),
            @ApiResponse(responseCode = "401", description = "Non authentifié")
    })
    public ResponseEntity<UserResponse> getCurrentUser(Authentication authentication) {
        UserResponse response = userService.getUserByEmail(authentication.getName());
        return ResponseEntity.ok(response);
    }

}
