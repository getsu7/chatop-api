package com.getsu.chatop.application.message;

import com.getsu.chatop.application.message.models.MessageRequest;
import com.getsu.chatop.domain.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> sendMessage(@RequestBody MessageRequest messageRequest) {
        messageService.sendMessage(messageRequest);
        return ResponseEntity.ok(Map.of("message", "Message send with success"));
    }
}

