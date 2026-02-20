package com.getsu.chatop.application.user;

import com.getsu.chatop.application.auth.models.UserResponse;
import com.getsu.chatop.domain.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    @Operation(summary = "Récupérer les informations d'un utilisateur via son id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informations utilisateur récupérées avec succès"),
            @ApiResponse(responseCode = "401", description = "Non authentifié")
    })
    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer id) {
        UserResponse response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }
}


