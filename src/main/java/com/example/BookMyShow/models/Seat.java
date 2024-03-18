package com.example.BookMyShow.models;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel {
    private int seatNumber;

    @OneToOne
    private SeatType seatType;

    private int rowNum;
    private int colNum;


}
