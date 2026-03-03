package com.getsu.chatop.application.rental;

import com.getsu.chatop.application.auth.constants.ApiResponseExamples;
import com.getsu.chatop.application.rental.models.RentalRequest;
import com.getsu.chatop.application.rental.models.RentalResponse;
import com.getsu.chatop.application.rental.models.RentalsResponse;
import com.getsu.chatop.domain.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.ErrorResponse;
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
            @ApiResponse(
                    responseCode = "401",
                    description = "Non authentifié",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = ApiResponseExamples.UNAUTHORIZED_ERROR_EXAMPLE)
                    )
            )
    })
    public ResponseEntity<RentalsResponse> getAllRentals() {
        RentalsResponse response = rentalService.getAllRentals();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une location à partir de son id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location récupérée avec succès"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Non authentifié",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = ApiResponseExamples.UNAUTHORIZED_ERROR_EXAMPLE)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Location non trouvée",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = ApiResponseExamples.NOT_FOUND_ERROR_EXAMPLE)
                    )
            )
    })
    public ResponseEntity<RentalResponse> getRentalById(@PathVariable Integer id) {
        RentalResponse response = rentalService.getRentalById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(summary = "Créer une location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location créée avec succès"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erreur de validation",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = ApiResponseExamples.VALIDATION_ERROR_EXAMPLE)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Non authentifié",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = ApiResponseExamples.UNAUTHORIZED_ERROR_EXAMPLE)
                    )
            )
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
            @ApiResponse(
                    responseCode = "400",
                    description = "Erreur de validation",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = ApiResponseExamples.VALIDATION_ERROR_EXAMPLE)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Non authentifié",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = ApiResponseExamples.UNAUTHORIZED_ERROR_EXAMPLE)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Location non trouvée",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = ApiResponseExamples.NOT_FOUND_ERROR_EXAMPLE)
                    )
            )
    })
    public ResponseEntity<Map<String, String>> updateRental(
            @PathVariable Integer id,
            @ModelAttribute RentalRequest rentalRequest,
            Authentication authentication) {
        rentalService.updateRental(id, rentalRequest, authentication.getName());
        return ResponseEntity.ok(Map.of("message", "Rental updated !"));
    }
}