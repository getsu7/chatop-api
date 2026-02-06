package com.getsu.chatop.application.rental.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalsResponse {
    private List<RentalResponse> rentals;
}
