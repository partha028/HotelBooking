package com.projects.lakeSide_hotel.serviceImpl;

import com.projects.lakeSide_hotel.exception.InvalidBookingRequestException;
import com.projects.lakeSide_hotel.model.BookedRoom;
import com.projects.lakeSide_hotel.model.Room;
import com.projects.lakeSide_hotel.repository.BookingRepository;
import com.projects.lakeSide_hotel.service.IBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService implements IBookingService {

    private final BookingRepository bookingRepository;
    private final RoomServiceImpl roomService;
    public List<BookedRoom> getAllBookingsByRoomId(Long roomId) {
        return bookingRepository.findByRoomId(roomId);
    }

    @Override
    public List<BookedRoom> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public BookedRoom findByBookingConfirmationCode(String confirmationCode) {
        return bookingRepository.findByBookingConfirmationCode(confirmationCode);
    }

    @Override
    public String saveBooking(Long roomId, BookedRoom bookingRequest) {
        if(bookingRequest.getCheckOutDate().isBefore(bookingRequest.getCheckInDate())){
            throw new InvalidBookingRequestException("Check-in date must be prior to check-out date");
        }
        Room room = roomService.getRoomById(roomId).get();
        List<BookedRoom> bookings = room.getBookings();
        boolean roomIsAvailable = roomIsAvailable(bookingRequest, bookings);
        if(roomIsAvailable){
            room.addBookings(bookingRequest);
            bookingRepository.save(bookingRequest);
        }else{
            throw new InvalidBookingRequestException("This room is not available for the selected dates");
        }
        return bookingRequest.getBookingConfirmationCode();
    }

    private boolean roomIsAvailable(BookedRoom bookingRequest, List<BookedRoom> bookings) {
        return bookings.stream().noneMatch(booking ->
                bookingRequest.getCheckInDate().isBefore(booking.getCheckOutDate()) &&
                        bookingRequest.getCheckOutDate().isAfter(booking.getCheckInDate()));
    }

    @Override
    public void cancelBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }
}
