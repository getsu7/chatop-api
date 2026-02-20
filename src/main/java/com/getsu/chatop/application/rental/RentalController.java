package com.getsu.chatop.application.rental;

import com.getsu.chatop.application.rental.models.RentalRequest;
import com.getsu.chatop.application.rental.models.RentalResponse;
import com.getsu.chatop.application.rental.models.RentalsResponse;
import com.getsu.chatop.domain.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    @Operation(summary = "Récupérer toutes les locations d'un utilisateur authentifié")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des locations récupérée avec succès"),
            @ApiResponse(responseCode = "401", description = "Non authentifié")
    })
    public ResponseEntity<RentalsResponse> getAllRentals() {
        RentalsResponse response = rentalService.getAllRentals();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une location à partir de son id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location récupérée avec succès"),
            @ApiResponse(responseCode = "401", description = "Non authentifié")
    })
    public ResponseEntity<RentalResponse> getRentalById(@PathVariable Integer id) {
        RentalResponse response = rentalService.getRentalById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(summary = "Créer une location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location créée avec succès"),
            @ApiResponse(responseCode = "401", description = "Non authentifié")
    })
    public ResponseEntity<Map<String, String>> createRental(
            @ModelAttribute RentalRequest rentalRequest,
            Authentication authentication) {
        rentalService.createRental(rentalRequest, authentication.getName());
        return ResponseEntity.ok(Map.of("message", "Rental created !"));
    }


    @PutMapping("/{id}")
    @Operation(summary = "Modifier une location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location modifiée avec succès"),
            @ApiResponse(responseCode = "401", description = "Non authentifié")
    })
    public ResponseEntity<Map<String, String>> updateRental(
            @PathVariable Integer id,
            @ModelAttribute RentalRequest rentalRequest,
            Authentication authentication) {
        rentalService.updateRental(id, rentalRequest, authentication.getName());
        return ResponseEntity.ok(Map.of("message", "Rental updated !"));
    }
}