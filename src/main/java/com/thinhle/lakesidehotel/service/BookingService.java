package com.thinhle.lakesidehotel.service;


import com.thinhle.lakesidehotel.exception.InvalidBookingResquestException;
import com.thinhle.lakesidehotel.model.BookedRoom;
import com.thinhle.lakesidehotel.model.Room;
import com.thinhle.lakesidehotel.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService implements IBookingService {
    
  private final BookingRepository bookingRepository ;

    private final IRoomService roomService;


    @Override
    public List<BookedRoom> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<BookedRoom> getAllBookingsByRoomId(Long roomId) {
            return bookingRepository.findByRoomId(roomId);
    }


    @Override
    public void cancelBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    @Override
    public String saveBooking(Long roomId, BookedRoom bookingRequest) {
        if(bookingRequest.getCheck_Out_Date().isBefore(bookingRequest.getCheck_In_Date())) {
            throw new InvalidBookingResquestException("Check-in date must come before check out date");
        }
        Room room = roomService.getRoomById(roomId).get();
        List<BookedRoom> existingbookings = room.getBookings();
        boolean roomIsAvailable = roomIsAvailable(bookingRequest,existingbookings);
        if(roomIsAvailable) {

            room.addBooking(bookingRequest);
            bookingRepository.save(bookingRequest);
        }else {
                throw new InvalidBookingResquestException("sorry, This room is not available for the selected dates");
        }

        return bookingRequest.getBookingConfirmationCode();
    }

    private boolean roomIsAvailable(BookedRoom bookingRequest, List<BookedRoom> existingBookings) {
        return existingBookings.stream()
                .noneMatch(existingBooking ->
                        bookingRequest.getCheck_In_Date().equals(existingBooking.getCheck_In_Date())
                                || bookingRequest.getCheck_Out_Date().isBefore(existingBooking.getCheck_Out_Date())
                                || (bookingRequest.getCheck_In_Date().isAfter(existingBooking.getCheck_In_Date())
                                && bookingRequest.getCheck_In_Date().isBefore(existingBooking.getCheck_Out_Date()))
                                || (bookingRequest.getCheck_In_Date().isBefore(existingBooking.getCheck_In_Date())

                                && bookingRequest.getCheck_Out_Date().equals(existingBooking.getCheck_Out_Date()))
                                || (bookingRequest.getCheck_In_Date().isBefore(existingBooking.getCheck_In_Date())

                                && bookingRequest.getCheck_Out_Date().isAfter(existingBooking.getCheck_Out_Date()))

                                || (bookingRequest.getCheck_In_Date().equals(existingBooking.getCheck_Out_Date())
                                && bookingRequest.getCheck_Out_Date().equals(existingBooking.getCheck_In_Date()))

                                || (bookingRequest.getCheck_In_Date().equals(existingBooking.getCheck_Out_Date())
                                && bookingRequest.getCheck_Out_Date().equals(bookingRequest.getCheck_In_Date()))
                );
    }

    @Override
    public Optional<BookedRoom> findByBookingConfirmationCode(String confirmationCode) {
        return bookingRepository.findByBookingConfirmationCode(confirmationCode);
    }

}
