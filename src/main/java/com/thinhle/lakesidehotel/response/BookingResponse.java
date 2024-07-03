package com.thinhle.lakesidehotel.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {


    private Long id;


    private LocalDate checkInDate;


    private LocalDate checkOutDate;


    private String guestFullName;


    private String guestEmail;


    private int NumOfAdults;


    private int NumOfChildren;


    private int totalNumberOfGuest;


    private String bookingConfirmationCode;



    public BookingResponse(Long bookingId, LocalDate checkInDate, LocalDate checkOutDate, String guestFullName, String guestEmail, int numOfAdults, int numOfChildren, int totalNumberOfGuest, String bookingConfirmationCode, RoomResponse room) {
            this.id = bookingId;
            this.checkInDate = checkInDate;
            this.checkOutDate = checkOutDate;
            this.guestFullName = guestFullName;
            this.guestEmail = guestEmail;
            this.NumOfAdults = numOfAdults;
            this.NumOfChildren = numOfChildren;
            this.totalNumberOfGuest = totalNumberOfGuest;
            this.bookingConfirmationCode = bookingConfirmationCode;


    }
}
