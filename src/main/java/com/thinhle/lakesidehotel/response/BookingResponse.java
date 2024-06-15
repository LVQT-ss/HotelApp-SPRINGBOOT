package com.thinhle.lakesidehotel.response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @Column(name = "check_In")
    private LocalDate check_In_Date;

    @Column(name = "check_Out")
    private LocalDate check_Out_Date;

    @Column(name = "Guest_FullName")
    private String guestFullname;

    @Column(name = "guest_Email")
    private String guestEmail;

    @Column(name = "children")
    private int NumOfAdults;

    @Column(name = "children")
    private int NumOfChildren;

    @Column(name = "total_guest")
    private int totalNumberOfGuest;

    @Column(name = "confirmation_Code")
    private String bookingConfirmationCode;

    public BookingResponse(Long bookingId, LocalDate check_In_Date, LocalDate check_Out_Date, String bookingConfirmationCode) {
        this.bookingId = bookingId;
        this.check_In_Date = check_In_Date;
        this.check_Out_Date = check_Out_Date;
        this.bookingConfirmationCode = bookingConfirmationCode;
    }
}
