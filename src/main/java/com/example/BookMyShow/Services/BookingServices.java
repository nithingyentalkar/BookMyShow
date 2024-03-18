package com.example.BookMyShow.Services;

import com.example.BookMyShow.Repositories.BookingRepository;
import com.example.BookMyShow.Repositories.ShowRepository;
import com.example.BookMyShow.Repositories.ShowSeatRepository;
import com.example.BookMyShow.Repositories.UserRepository;
import com.example.BookMyShow.dtos.BookingMovieRequestdto;
import com.example.BookMyShow.exceptions.InvalidShowException;
import com.example.BookMyShow.exceptions.InvalidUserException;
import com.example.BookMyShow.exceptions.SeatNotAvailable;
import com.example.BookMyShow.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServices {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private ShowSeatRepository showSeatRepository;
    @Autowired
    private BookingRepository bookingRepository;
    public Booking bookMovie(BookingMovieRequestdto request) throws InvalidUserException, InvalidShowException, SeatNotAvailable {

        Long userId = request.getUserId();
        List<Long> showSeatIds = request.getShowSeatIds();
        Long showId = request.getShowId();

        //1. Get User ID
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) throw new InvalidUserException();

        //2. Get Show ID
        Optional<Show> show = showRepository.findById(showId);
        if(show.isEmpty()) throw new InvalidShowException();

        List<ShowSeat> showSeats = ReserveShowSeat(showSeatIds);


        Booking booking = new Booking();
        booking.setBookingstatus(Bookingstatus.WAITING);
        booking.setShowSeatList(showSeats);
        booking.setPaymentList(new ArrayList<>());
        booking.setAmount(CalculateAmount(showSeats));
        booking.setUser(user.get());
        booking.setShow(show.get());

       return bookingRepository.save(booking);
    }

    private int CalculateAmount(List<ShowSeat> showSeats) {
        //Calculate the amount of the Seats
        return 0;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<ShowSeat> ReserveShowSeat(List<Long> showSeatIds) throws SeatNotAvailable {


        /*
        <<START TRANSACTION>>
            1. get ShowSeat IDs
            2. Check if they are available
            ->if They are not available or they are Locked < 10
            throw an error stating - Booking is not available
            ->if they available Lock them and update the time with current lock time
            3. Save the showSeatIds in DB
         <<END TRANSACTION>>
         */
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        for(ShowSeat showSeat : showSeats){
            if(!(showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)
                    || (showSeat.getShowSeatStatus().equals(ShowSeatStatus.LOCKED)
                    && Duration.between(new Date().toInstant(), showSeat.getLockedAt().toInstant()).toMinutes()<10 ))){
                   throw new SeatNotAvailable();
            }
        }

        for(ShowSeat showSeat: showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.LOCKED);
            showSeat.setLockedAt(new Date());
        }

        return showSeatRepository.saveAll(showSeats);
    }
}
