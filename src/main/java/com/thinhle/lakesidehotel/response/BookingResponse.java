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


    private LocalDate check_In_Date;


    private LocalDate check_Out_Date;


    private String guestFullname;


    private String guestEmail;


    private int NumOfAdults;


    private int NumOfChildren;


    private int totalNumberOfGuest;


    private String bookingConfirmationCode;

    public BookingResponse(Long id, LocalDate check_In_Date, LocalDate check_Out_Date, String bookingConfirmationCode, String guestEmail, int numOfAdults, int numOfChildren, int totalNumberOfGuest, String confirmationCode, RoomResponse room) {
        this.id = id;
        this.check_In_Date = check_In_Date;
        this.check_Out_Date = check_Out_Date;
        this.bookingConfirmationCode = bookingConfirmationCode;
        this.guestFullname = guestEmail;
        this.totalNumberOfGuest = totalNumberOfGuest;
    }


}
