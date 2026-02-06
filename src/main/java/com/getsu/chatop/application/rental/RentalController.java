package com.getsu.chatop.application.rental;

import com.getsu.chatop.application.rental.models.RentalRequest;
import com.getsu.chatop.application.rental.models.RentalResponse;
import com.getsu.chatop.application.rental.models.RentalsResponse;
import com.getsu.chatop.domain.service.RentalService;
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
    public ResponseEntity<RentalsResponse> getAllRentals() {
        RentalsResponse response = rentalService.getAllRentals();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalResponse> getRentalById(@PathVariable Integer id) {
        RentalResponse response = rentalService.getRentalById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createRental(
            @ModelAttribute RentalRequest rentalRequest,
            Authentication authentication) {
        rentalService.createRental(rentalRequest, authentication.getName());
        return ResponseEntity.ok(Map.of("message", "Rental created !"));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateRental(
            @PathVariable Integer id,
            @ModelAttribute RentalRequest rentalRequest,
            Authentication authentication) {
        rentalService.updateRental(id, rentalRequest, authentication.getName());
        return ResponseEntity.ok(Map.of("message", "Rental updated !"));
    }
}