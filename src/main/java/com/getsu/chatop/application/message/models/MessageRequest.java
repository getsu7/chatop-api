package com.getsu.chatop.application.message.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageRequest {
    private String message;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("rental_id")
    private Integer rentalId;
}
