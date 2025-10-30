package src.interfaces;

import src.models.Booking;
import java.time.LocalDate;
import java.util.List;

public interface BookingDAO {
    void bookRoom(Booking booking) throws Exception;
    List<Booking> getAllBookings();
    List<Booking> getBookingsByCustomerEmail(String email);
    void cancelBooking(int bookingId) throws Exception;

    List<Object[]> countBookingsPerCustomer();
    double getAverageBookedRoomPrice();
    List<Integer> getAvailableRoomsBetween(LocalDate start, LocalDate end);
    List<String> getCustomersWhoNeverBooked();
}
