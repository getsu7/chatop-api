package com.getsu.chatop.domain.service;

import com.getsu.chatop.application.rental.models.RentalRequest;
import com.getsu.chatop.application.rental.models.RentalResponse;
import com.getsu.chatop.application.rental.models.RentalsResponse;
import com.getsu.chatop.infrastructure.models.Rental;
import com.getsu.chatop.infrastructure.models.User;
import com.getsu.chatop.infrastructure.repository.RentalRepository;
import com.getsu.chatop.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;

    public RentalService(RentalRepository rentalRepository, UserRepository userRepository) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
    }

    public RentalsResponse getAllRentals() {
        List<Rental> rentals = rentalRepository.findAll();
        List<RentalResponse> rentalResponses = rentals.stream()
                .map(this::convertToResponse)
                .toList();
        return new RentalsResponse(rentalResponses);
    }

    public RentalResponse getRentalById(Integer id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Location introuvable"));
        return convertToResponse(rental);
    }

    public void createRental(RentalRequest rentalRequest, String ownerEmail) {
        User owner = userRepository.findByEmail(ownerEmail);
        if (owner == null) {
            throw new IllegalArgumentException("Utilisateur introuvable");
        }

        Rental rental = new Rental();
        rental.setName(rentalRequest.getName());
        rental.setSurface(rentalRequest.getSurface());
        rental.setPrice(rentalRequest.getPrice());
        rental.setDescription(rentalRequest.getDescription());
        rental.setOwner(owner);

        if (rentalRequest.getPicture() != null && !rentalRequest.getPicture().isEmpty()) {
            String pictureUrl = savePicture(rentalRequest.getPicture());
            rental.setPicture(pictureUrl);
        }

        rentalRepository.save(rental);
    }

    public void updateRental(Integer id, RentalRequest rentalRequest, String ownerEmail) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Location introuvable"));

        User owner = userRepository.findByEmail(ownerEmail);
        if (owner == null || !rental.getOwner().getId().equals(owner.getId())) {
            throw new IllegalArgumentException("Non autorisé à modifier cette location");
        }

        if (rentalRequest.getName() != null) {
            rental.setName(rentalRequest.getName());
        }
        if (rentalRequest.getSurface() != null) {
            rental.setSurface(rentalRequest.getSurface());
        }
        if (rentalRequest.getPrice() != null) {
            rental.setPrice(rentalRequest.getPrice());
        }
        if (rentalRequest.getDescription() != null) {
            rental.setDescription(rentalRequest.getDescription());
        }
        if (rentalRequest.getPicture() != null && !rentalRequest.getPicture().isEmpty()) {
            String pictureUrl = savePicture(rentalRequest.getPicture());
            rental.setPicture(pictureUrl);
        }

        rentalRepository.save(rental);
    }

    private RentalResponse convertToResponse(Rental rental) {
        RentalResponse response = new RentalResponse();
        response.setId(rental.getId());
        response.setName(rental.getName());
        response.setSurface(rental.getSurface());
        response.setPrice(rental.getPrice());
        response.setPicture(rental.getPicture());
        response.setDescription(rental.getDescription());
        response.setOwnerId(rental.getOwner().getId());
        response.setCreatedAt(rental.getCreatedAt());
        response.setUpdatedAt(rental.getUpdatedAt());
        return response;
    }

    private String savePicture(MultipartFile file) {
        try {
            String uploadDir = "uploads/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return "/uploads/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la sauvegarde de l'image", e);
        }
    }
}

