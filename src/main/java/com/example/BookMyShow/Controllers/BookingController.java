package com.example.BookMyShow.Controllers;

import com.example.BookMyShow.Services.BookingServices;
import com.example.BookMyShow.dtos.BookingMovieRequestdto;
import com.example.BookMyShow.dtos.BookingMovieResponsedto;
import com.example.BookMyShow.dtos.ResponseStatus;
import com.example.BookMyShow.exceptions.InvalidShowException;
import com.example.BookMyShow.exceptions.InvalidUserException;
import com.example.BookMyShow.exceptions.SeatNotAvailable;
import com.example.BookMyShow.models.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    @Autowired
    private BookingServices bookingServices;
    public BookingMovieResponsedto bookMovie(BookingMovieRequestdto request){
        try {
            Booking booking = bookingServices.bookMovie(request);
           return new BookingMovieResponsedto(booking.getId(), booking.getAmount(), ResponseStatus.SUCCESS, "YOUR BOOKINg IS SUCCESSFULL");
        } catch (InvalidUserException e) {
            return new BookingMovieResponsedto(null,0, ResponseStatus.FAILURE, "User Not Found, Create a New User");
        } catch (InvalidShowException e) {
            return new BookingMovieResponsedto(null,0, ResponseStatus.FAILURE, "Show Not Found, Please enter Valid Show number");
        } catch (SeatNotAvailable e) {
            return new BookingMovieResponsedto(null,0, ResponseStatus.FAILURE, "Seats Are Not Available");
        }

    }
}
