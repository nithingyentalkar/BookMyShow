package com.example.BookMyShow.dtos;

import jakarta.websocket.server.ServerEndpoint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignupResponsedto {

    private Long userID;
    private ResponseStatus responseStatus;
}
