package com.thinhle.lakesidehotel.service;


import com.thinhle.lakesidehotel.model.BookedRoom;
import com.thinhle.lakesidehotel.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService implements IBookingService {
    
  private final BookingRepository bookingRepository ;

    private final IRoomService roomService;


    @Override
    public List<BookedRoom> getAllBookings() {
        return null;
    }

        public List<BookedRoom> getAllBookingsByRoomId(Long roomId) {
            return null;
        }


    @Override
    public void cancelBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    @Override
    public String saveBooking(Long roomId, BookedRoom bookingRequest) {
        return "";
    }

    @Override
    public BookedRoom findByBookingConfirmationCode(String confirmationCode) {
        return null;
    }

}
