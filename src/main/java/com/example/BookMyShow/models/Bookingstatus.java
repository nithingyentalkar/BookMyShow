package com.example.BookMyShow.models;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


public enum Bookingstatus {
    BOOKED, WAITING, FAILED,CANCELLED
}
