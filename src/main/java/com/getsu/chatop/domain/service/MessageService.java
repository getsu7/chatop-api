package com.getsu.chatop.domain.service;

import com.getsu.chatop.application.message.models.MessageRequest;
import com.getsu.chatop.infrastructure.models.Message;
import com.getsu.chatop.infrastructure.models.Rental;
import com.getsu.chatop.infrastructure.models.User;
import com.getsu.chatop.infrastructure.repository.MessageRepository;
import com.getsu.chatop.infrastructure.repository.RentalRepository;
import com.getsu.chatop.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;

    public MessageService(MessageRepository messageRepository,
                          UserRepository userRepository,
                          RentalRepository rentalRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.rentalRepository = rentalRepository;
    }

    public void sendMessage(MessageRequest messageRequest) {
        User user = userRepository.findById(messageRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable"));

        Rental rental = rentalRepository.findById(messageRequest.getRentalId())
                .orElseThrow(() -> new IllegalArgumentException("Location introuvable"));

        Message message = new Message();
        message.setUser(user);
        message.setRental(rental);
        message.setMessage(messageRequest.getMessage());

        messageRepository.save(message);
    }
}

