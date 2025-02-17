package com.thinhle.lakesidehotel.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.tomcat.util.codec.binary.Base64;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class RoomResponse {
    private Long id;

    private String roomType;

    private BigDecimal roomPrice;

    private boolean isBooked;

    private String photo;

    private List<BookingResponse> bookings;


    public RoomResponse(Long id, String roomType, BigDecimal roomPrice) {
        this.id = id;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
    }

    public RoomResponse(Long id, String roomType, BigDecimal roomPrice, boolean isBooked, byte[] photoBytes) {
        this.id = id;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.isBooked = isBooked;
        this.photo = photoBytes != null ? Base64.encodeBase64String(photoBytes) : null;
//        this.bookings = bookings;
    }

    public RoomResponse(Long id, String roomType, BigDecimal roomPrice, boolean booked, byte[] photoBytes, List<BookingResponse> bookingInfo) {
    }
}
