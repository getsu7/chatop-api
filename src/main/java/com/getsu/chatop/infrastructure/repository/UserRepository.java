package com.getsu.chatop.infrastructure.repository;

import com.getsu.chatop.infrastructure.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
