package com.example.BookMyShow.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookingMovieResponsedto {
    private Long bookingId;
    private int amount;
    private ResponseStatus responseStatus;
    private String responseMessage;
}
