package com.getsu.chatop.infrastructure.repository;

import com.getsu.chatop.infrastructure.models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {
}

