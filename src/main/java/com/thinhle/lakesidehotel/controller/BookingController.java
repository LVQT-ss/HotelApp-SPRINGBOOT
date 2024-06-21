package com.thinhle.lakesidehotel.controller;

import com.thinhle.lakesidehotel.exception.InvalidBookingResquestException;
import com.thinhle.lakesidehotel.exception.ResourceNotFoundException;
import com.thinhle.lakesidehotel.model.BookedRoom;
import com.thinhle.lakesidehotel.response.BookingResponse;
import com.thinhle.lakesidehotel.service.IBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final IBookingService bookingService;


    @GetMapping("all-bookings")
    public ResponseEntity<List<BookingResponse>> getAllBookings() {
        List<BookedRoom> booking = bookingService.getAllBookings();
        List<BookingResponse> bookingResponses = new ArrayList<>();
        for (BookedRoom bookings : booking) {
            BookingResponse bookingResponse = getBookingResponse(booking);
            bookingResponses.add(bookingResponse);
        }
        return ResponseEntity.ok(bookingResponses);
    }

    @GetMapping("/confirmation/{confirmationCode}")
    public ResponseEntity<?> getBookingConfirmationCode(@PathVariable  String confirmationCode) {
            try {
                BookedRoom booking = bookingService.findByBookingConfirmationCode(confirmationCode);
                BookingResponse bookingResponse = getBookingResponse(booking);
                return ResponseEntity.ok(bookingResponse);
            } catch(ResourceNotFoundException ex){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
            }
    }
    @PostMapping("/room/{roomId}/booking")
    public ResponseEntity<?> saveBooking(@PathVariable  Long roomId,
                                         @RequestBody BookedRoom bookingRequest){
        try{
            String confirmationCode = bookingService.saveBooking(roomId,bookingRequest);
            return ResponseEntity.ok("Room booked sucessefully, Your confirmation code is: "+ confirmationCode);
        } catch(InvalidBookingResquestException e){
                    return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
