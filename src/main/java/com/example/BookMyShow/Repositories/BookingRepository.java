package com.example.BookMyShow.Repositories;

import com.example.BookMyShow.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {
}
