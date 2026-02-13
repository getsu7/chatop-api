package com.getsu.chatop.infrastructure.repository;

import com.getsu.chatop.infrastructure.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
}

