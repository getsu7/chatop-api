package com.getsu.chatop.application.message;

import com.getsu.chatop.application.message.models.MessageRequest;
import com.getsu.chatop.domain.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Envoyer un message")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message envoyé avec succès"),
            @ApiResponse(responseCode = "401", description = "Non authentifié"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    public ResponseEntity<Map<String, String>> sendMessage(@RequestBody MessageRequest messageRequest) {
        messageService.sendMessage(messageRequest);
        return ResponseEntity.ok(Map.of("message", "Message send with success"));
    }
}

